package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    private final String mazeFile;
    private Integer[][] maze;
    private Integer[] start;
    private Integer[] end;

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

    public void convertTo2DArray() throws IOException {
        int[] size = mazeSize();
        Integer[][] maze = new Integer[size[0]][size[1]];

        BufferedReader reader = new BufferedReader(new FileReader(this.mazeFile));
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    maze[i][idx] = 1;
                } else if (line.charAt(idx) == ' ') {
                    if (i == 0) {
                        this.start = new Integer[] {i, idx};
                    } else if (i == size[0] - 1) {
                        this.end = new Integer[] {i, idx};
                    }
                    maze[i][idx] = 0;
                }
            }
            i++;
        }
        this.maze = maze;
    }
}
