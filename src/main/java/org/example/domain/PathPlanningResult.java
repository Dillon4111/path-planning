package org.example.domain;

import java.util.ArrayList;

public class PathPlanningResult {
    private final int totalValue;
    private ArrayList<Plane> path = new ArrayList<>();

    public PathPlanningResult(int totalValue, ArrayList<Plane> path) {
        this.totalValue = totalValue;
        this.path = path;
    }

    public int getTotalValue() {
        return this.totalValue;
    }

    public ArrayList<Plane> getPath() { return path; }
}
