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
            Integer[] newPosition;

            if (maze.checkEmptyCell(rightCell)) {
                newPosition = rightCell;
                path += "R";
            }
            else if (maze.checkEmptyCell(frontCell)) {
                newPosition = frontCell;
                path += "F";
            } else {
                Integer[] cell = maze.move(maze.turnRight(maze.facing), mazePointer);
                while (!maze.checkEmptyCell(cell)) {
                    maze.facing = maze.turnRight(maze.facing);
                    cell = maze.move(maze.turnRight(maze.facing), mazePointer);
                    path += "R";
                }
                newPosition = cell;
            }
            mazePointer = newPosition;
        }
        return path;
    }

    public static void checkIfSolves(Maze maze) {
        // TODO: implement this method
    }
}
