package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    public static void printMaze(String mazeFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
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
}
