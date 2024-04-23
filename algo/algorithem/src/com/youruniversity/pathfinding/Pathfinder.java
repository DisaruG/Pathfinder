package com.youruniversity.pathfinding;

import java.util.*;

public class Pathfinder {
    private Grid grid;
    private final int[] dx = {-1, 1, 0, 0}; // Up, Down, Left, Right movements
    private final int[] dy = {0, 0, -1, 1};

    public Pathfinder(Grid grid) {
        this.grid = grid;
    }

    public List<Position> findPath() {
        Position start = grid.getStart();
        Position finish = grid.getFinish();
        char[][] map = grid.getGrid();
        Map<Position, Position> parentMap = new HashMap<>();
        Queue<Position> queue = new LinkedList<>();
        queue.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (current.equals(finish)) {
                return reconstructPath(parentMap, finish);
            }

            for (int i = 0; i < dx.length; i++) {
                Position next = slide(current, i, map);
                if (!parentMap.containsKey(next)) {
                    parentMap.put(next, current);
                    queue.add(next);
                }
            }
        }
        return Collections.emptyList(); // No path found
    }

    private Position slide(Position start, int dir, char[][] map) {
        int x = start.getX();
        int y = start.getY();
        while (canMove(x + dx[dir], y + dy[dir], map)) {
            x += dx[dir];
            y += dy[dir];
        }
        return new Position(x, y);
    }

    private boolean canMove(int x, int y, char[][] map) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length && map[x][y] != '0';
    }

    private List<Position> reconstructPath(Map<Position, Position> parentMap, Position end) {
        LinkedList<Position> path = new LinkedList<>();
        Position step = end;
        while (step != null) {
            path.addFirst(step);
            step = parentMap.get(step);
        }
        return path;
    }
}
