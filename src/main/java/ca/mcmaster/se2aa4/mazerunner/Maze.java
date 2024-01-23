package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

enum Facing {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class Maze {

    private final String mazeFile;
    public Integer[][] maze;
    public Integer[] start;
    public Integer[] end;
    public Facing facing;

    public Maze(String mazeFile) {
        this.mazeFile = mazeFile;
    }

    public void printMaze() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.mazeFile));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("PASS ");
                }
            }
            System.out.print(System.lineSeparator());
        }
    }

    private int[] mazeSize() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.mazeFile));
        String line;
        int[] size = new int[2];

        while ((line = reader.readLine()) != null) {
            size[0] += 1;
            size[1] = line.length();
        }
        return size;
    }

    public void findEntryExit() {
        for (int i = 0; i < this.maze.length; i++) {
            for (int j = 0; j < this.maze[0].length; j++) {
                if (this.maze[i][j] == 0) {
                    if (i == 0) {
                        this.start = new Integer[] {i, j};
                        this.facing = Facing.DOWN;
                    } else if (j == 0) {
                        this.start = new Integer[] {i, j};
                        this.facing = Facing.RIGHT;
                    } else if (j == this.maze[0].length - 1) {
                        this.end = new Integer[]{i, j};
                    } else if (i == this.maze.length - 1) {
                        this.end = new Integer[]{i, j};
                    }
                }
            }
        }
    }

    public void convertTo2DArray() throws IOException {
        int[] size = mazeSize();
        Integer[][] maze = new Integer[size[0]][size[1]];

        // Default zeros
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                maze[i][j] = 0;
            }
        }

        BufferedReader reader = new BufferedReader(new FileReader(this.mazeFile));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    maze[i][idx] = 1;
                } else {
                    maze[i][idx] = 0;
                }
            }
            i++;
        }
        this.maze = maze;
    }

    // Check if cell is empty (true if empty, false if wall)
    public boolean checkEmptyCell(Integer[] cell) throws ArrayIndexOutOfBoundsException {
        try {
            return this.maze[cell[0]][cell[1]] == 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public Integer[] getMovement(Facing facing) {
        return switch (facing) {
            case UP -> new Integer[]{-1, 0};
            case DOWN -> new Integer[]{1, 0};
            case LEFT -> new Integer[]{0, -1};
            case RIGHT -> new Integer[]{0, 1};
            default -> new Integer[]{0, 0};
        };
    }

    public Facing turnRight(Facing facing) {
        return switch (facing) {
            case UP -> Facing.RIGHT;
            case DOWN -> Facing.LEFT;
            case LEFT -> Facing.UP;
            case RIGHT -> Facing.DOWN;
            default -> facing;
        };
    }

    public Integer[] move(Facing facing, Integer[] position) {
        Integer[] movement = getMovement(facing);
        return new Integer[] {position[0] + movement[0], position[1] + movement[1]};
    }
}
