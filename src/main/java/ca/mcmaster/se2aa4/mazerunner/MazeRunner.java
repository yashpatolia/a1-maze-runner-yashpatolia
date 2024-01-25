package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Arrays;

public class MazeRunner {
    public static String solveMaze(Maze maze) throws IOException {
        String path = "";
        maze.convertTo2DArray();
        maze.findEntryExit();

        Integer[] mazePointer = maze.start;

        while (!Arrays.equals(mazePointer, maze.end)) {
            Integer[] rightCell = maze.move(maze.turnRight(maze.facing), mazePointer);
            Integer[] frontCell = maze.move(maze.facing, mazePointer);
            Integer[] leftCell = maze.move(maze.turnLeft(maze.facing), mazePointer);

            if (maze.checkEmptyCell(rightCell)) {
                maze.facing = maze.turnRight(maze.facing);
                mazePointer = rightCell;
                path += " R F";
            } else if (maze.checkEmptyCell(frontCell)) {
                mazePointer = frontCell;
                path += "F";
            } else if (maze.checkEmptyCell(leftCell)) {
                maze.facing = maze.turnLeft(maze.facing);
                mazePointer = leftCell;
                path += " L F";
            } else {
                maze.facing = maze.turnRight(maze.facing);
                maze.facing = maze.turnRight(maze.facing);
                path += " RR ";
            }
        }
        return path;
    }

    public static String checkIfSolves(Maze maze, String path) throws IOException {
        maze.convertTo2DArray();
        maze.findEntryExit();

        Integer[] mazePointer = maze.start;

        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'F') {
                mazePointer = maze.move(maze.facing, mazePointer);
                if (!maze.checkEmptyCell(mazePointer)) {
                    return "incorrect path";
                }
            } else if (path.charAt(i) == 'R') {
                maze.facing = maze.turnRight(maze.facing);
            } else if (path.charAt(i) == 'L') {
                maze.facing = maze.turnLeft(maze.facing);
            }
        }

        if (Arrays.equals(mazePointer, maze.end)) {
            return "correct path";
        } else {
            return "incorrect path";
        }
    }
}
