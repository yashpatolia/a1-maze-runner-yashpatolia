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

            String path = MazeRunner.solveMaze(maze);
            System.out.println(path);
        } catch(Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
            logger.error(e.getMessage());
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("i", true, "maze text file to read");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String mazeFile = cmd.getOptionValue("i");
        logger.info("**** Reading the maze from file " + mazeFile);
        return new Configuration(mazeFile);
    }

    private record Configuration(String mazeFile) {
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
