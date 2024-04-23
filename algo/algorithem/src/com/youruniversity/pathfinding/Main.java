package com.youruniversity.pathfinding;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Grid grid = new Grid("map.txt");
            Pathfinder pathfinder = new Pathfinder(grid);
            List<Position> path = pathfinder.findPath();
            printPath(path);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static void printPath(List<Position> path) {
        if (path.isEmpty()) {
            System.out.println("No path found.");
            return;
        }
        int step = 1;
        for (Position pos : path) {
            System.out.println(step + ". Move to " + pos);
            step++;
        }
    }
}
