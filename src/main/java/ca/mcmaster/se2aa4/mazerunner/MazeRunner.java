package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Arrays;

public class MazeRunner {
    public static String solveMaze(Maze maze) throws IOException {
        String path = "";
        maze.convertTo2DArray();
        maze.findEntryExit();

        Integer[] mazePointer = maze.start;
        int row = mazePointer[0];
        int column = mazePointer[1];
        Facing tempFacing = maze.facing;

        while (!Arrays.equals(mazePointer, maze.end)) {
            if (maze.facing == tempFacing) {
                Integer[] movement = maze.getMovement(maze.facing);
                Integer[] newPosition = new Integer[] {row + movement[0], column + movement[1]};

                if (maze.checkEmptyCell(newPosition)) {
                    row = newPosition[0];
                    column = newPosition[1];
                    mazePointer = newPosition;
                    path += "F";
                    tempFacing = maze.facing;
                } else {
                    if (maze.facing == Facing.UP) {
                        maze.facing = Facing.RIGHT;
                        path += "R";
                    } else if (maze.facing == Facing.DOWN) {
                        maze.facing = Facing.LEFT;
                        path += "L";
                    } else if (maze.facing == Facing.LEFT) {
                        maze.facing = Facing.UP;
                        path += "U";
                    } else if (maze.facing == Facing.RIGHT) {
                        maze.facing = Facing.DOWN;
                        path += "D";
                    }
                }
            }
        }

        return path;
    }

    public static void checkIfSolves(Maze maze) {
        // TODO: implement this method
    }
}
