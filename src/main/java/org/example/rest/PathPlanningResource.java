package org.example.rest;

import org.example.domain.PathPlanningResult;
import org.example.domain.Plane;
import org.example.service.PathPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PathPlanningResource {
    @Autowired
    private PathPlanningService pathPlanningService;

    @GetMapping("/calculatePath/{gridWidth}/{steps}/{ms}")
    public PathPlanningResult calculateBestPath(@PathVariable int gridWidth, @PathVariable int steps, @PathVariable long ms
    ) throws IOException {
        return pathPlanningService.calculateMostValuablePath(gridWidth, steps, ms,
                new Plane(0, new int[]{1, 1}));
    }
}
