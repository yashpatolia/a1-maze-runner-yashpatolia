package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();

    public static String solveMaze(Maze maze) throws IOException {
        String path = "";
        maze.convertTo2DArray();
        maze.findEntryExit();

        Integer[] mazePointer = maze.start;
        int row = mazePointer[0];
        int column = mazePointer[1];

        while (mazePointer != maze.end) {
            break;
        }

        return path;
    }

    public static void checkIfSolves(Maze maze) {
        // TODO: implement this method
    }
}
