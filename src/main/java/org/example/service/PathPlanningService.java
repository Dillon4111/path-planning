package org.example.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.example.domain.PathPlanningResult;
import org.example.domain.Plane;

public class PathPlanningService {
    private static final String PATH_TO_FILE = "src/main/resources/grids/";
    private static final int[][] planePerimeter = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };
    private int[][] grid;
    private final ArrayList<Plane> selectedPath = new ArrayList<>();
    private long endTime;

    public PathPlanningResult calculateMostValuablePath(int gridWidth, int totalTimeSteps, long maxDuration, Plane plane) throws IOException {
        endTime = System.currentTimeMillis() + maxDuration;

        File gridFile = new File(PATH_TO_FILE + gridWidth + ".txt");
        Scanner input = new Scanner(gridFile);
        grid = createGrid(gridWidth, input);
        input.close();

        selectedPath.add(plane);
        getNextBestPlane(plane, totalTimeSteps);

        printGrid(grid, gridWidth);
        for (Plane p : selectedPath) {
            System.out.println(Arrays.toString(p.getCoordinates()) + ", " + p.getValue());
        }
        return new PathPlanningResult(selectedPath.stream().mapToInt(Plane::getValue).sum(), selectedPath);
    }

    private int[][] createGrid(int gridWidth, Scanner input) {
        int[][] grid = new int[gridWidth][gridWidth];

        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridWidth; y++) {
                if (input.hasNextInt()) {
                    int nextTile = input.nextInt();
                    grid[x][y] = nextTile;
                }
            }
        }
        return grid;
    }

    private void getNextBestPlane(Plane currentPlane, int steps) {
        if (steps == 0 || (System.currentTimeMillis() >= endTime)) return;

        Plane bestPlane = currentPlane.clone();
        bestPlane.setValue(0);
        int[] currentCoordinates = currentPlane.getCoordinates();

        for (int[] plane : planePerimeter) {

            int x = currentCoordinates[0] + plane[0];
            int y = currentCoordinates[1] + plane[1];
            int[] nextCoordinatesSet = new int[]{x, y};

            int perimeterValue;
            try {
                perimeterValue = grid[nextCoordinatesSet[0]][nextCoordinatesSet[1]];
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }

            if (perimeterValue > bestPlane.getValue()) {
                bestPlane = new Plane(perimeterValue, nextCoordinatesSet);
            }
        }
        selectedPath.add(bestPlane);
        grid[bestPlane.getCoordinates()[0]][bestPlane.getCoordinates()[1]] = 0;
        updateGrid();
        getNextBestPlane(bestPlane, steps - 1);
    }

    private void updateGrid() {
        for(Plane p : selectedPath) {
            if (grid[p.getCoordinates()[0]][p.getCoordinates()[1]] < p.getValue()) {
                grid[p.getCoordinates()[0]][p.getCoordinates()[1]] ++;
            }
        }
    }

    private void printGrid(int[][] grid, int width) {
        System.out.println("Int Map:");

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                System.out.print(grid[x][y] + " ");
            }
            System.out.println();
        }
    }
}
