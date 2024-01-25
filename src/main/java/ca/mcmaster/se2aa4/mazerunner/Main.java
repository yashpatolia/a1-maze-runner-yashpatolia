package ca.mcmaster.se2aa4.mazerunner;

import java.io.File;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        try {
            Configuration config = configure(args);
            Maze maze = new Maze(config.mazeFile);

            if (config.verifyPath != null) {
                logger.info("**** Verifying path");
                String pathCheck = MazeRunner.checkIfSolves(maze, config.verifyPath);
                System.out.println(pathCheck);
            } else {
                logger.info("**** Computing path");
                String path = MazeRunner.solveMaze(maze);
                path = MazePath.getFactorizedPath(path);
                System.out.println(path);
            }
            logger.info("** End of MazeRunner");
        } catch(Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
            logger.error(e.getMessage());
        }
    }

    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", true, "maze text file to read");
        options.addOption("p", true, "path to verify");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String mazeFile = cmd.getOptionValue("i");
        String verifyPath = cmd.getOptionValue("p");
        logger.info("**** Reading the maze from file " + mazeFile);
        return new Configuration(mazeFile, verifyPath);
    }

    private record Configuration(String mazeFile, String verifyPath) {
        Configuration {
            if (mazeFile == null) {
                throw new IllegalArgumentException("Maze file must be specified");
            }
            File file = new File(mazeFile);
            if (!file.exists()) {
                throw new IllegalArgumentException("Maze file does not exist");
            }
        }
    }
}
