package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    private final String mazeFile;
    private Integer[][] maze;
    private Integer[] start;
    private Integer[] end;
    public Facing facing;

    public Maze(String mazeFile) {
        this.mazeFile = mazeFile;
    }

    public Integer[] getMazeStart() {
        return this.start;
    }

    public Integer[] getMazeEnd() {
        return this.end;
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
                    if (j == 0) {
                        this.start = new Integer[] {i, j};
                        this.facing = Facing.RIGHT;
                    } else if (j == this.maze[0].length - 1) {
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

    public boolean checkEmptyCell(Integer[] cell) throws ArrayIndexOutOfBoundsException {
        try {
            return this.maze[cell[0]][cell[1]] == 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
