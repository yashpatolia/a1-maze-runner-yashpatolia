package ca.mcmaster.se2aa4.mazerunner;

public class MazePath {
    public static String getFactorizedPath(String path) {
        String factorizedPath = "";
        String[] splitPath = path.split(" ");

        for (String s : splitPath) {
            int count = s.length();
            char letter = s.charAt(0);
            factorizedPath += String.format("%d%c ", count, letter);
        }

        return factorizedPath;
    }
}
