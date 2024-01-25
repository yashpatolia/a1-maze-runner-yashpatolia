package ca.mcmaster.se2aa4.mazerunner;

public class MazeMove {
    private static Integer[] getMovement(Facing facing) {
        return switch (facing) {
            case UP -> new Integer[]{-1, 0};
            case DOWN -> new Integer[]{1, 0};
            case LEFT -> new Integer[]{0, -1};
            case RIGHT -> new Integer[]{0, 1};
            default -> new Integer[]{0, 0};
        };
    }

    public static Integer[] move(Facing facing, Integer[] position) {
        Integer[] movement = getMovement(facing);
        return new Integer[] {position[0] + movement[0], position[1] + movement[1]};
    }
}
