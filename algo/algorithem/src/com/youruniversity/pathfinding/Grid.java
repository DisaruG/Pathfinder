package com.youruniversity.pathfinding;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Grid {
    private char[][] grid;
    private Position start;
    private Position finish;

    public Grid(String filename) throws IOException {
        parseMapFromFile(filename);
    }

    private void parseMapFromFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();
            if (lines.get(i).indexOf('S') != -1) {
                start = new Position(i, lines.get(i).indexOf('S'));
            }
            if (lines.get(i).indexOf('F') != -1) {
                finish = new Position(i, lines.get(i).indexOf('F'));
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public Position getStart() {
        return start;
    }

    public Position getFinish() {
        return finish;
    }
}

