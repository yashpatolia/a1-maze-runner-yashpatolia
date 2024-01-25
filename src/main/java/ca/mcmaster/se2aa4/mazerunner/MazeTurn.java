package ca.mcmaster.se2aa4.mazerunner;

enum Facing {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class MazeTurn {
    public static Facing turn(Facing facing, String direction) {
        return switch (direction) {
            case "R" -> turnRight(facing);
            case "L" -> turnLeft(facing);
            default -> facing;
        };
    }

    private static Facing turnRight(Facing facing) {
        return switch (facing) {
            case UP -> Facing.RIGHT;
            case DOWN -> Facing.LEFT;
            case LEFT -> Facing.UP;
            case RIGHT -> Facing.DOWN;
            default -> facing;
        };
    }

    private static Facing turnLeft(Facing facing) {
        return switch (facing) {
            case UP -> Facing.LEFT;
            case DOWN -> Facing.RIGHT;
            case LEFT -> Facing.DOWN;
            case RIGHT -> Facing.UP;
            default -> facing;
        };
    }
}
