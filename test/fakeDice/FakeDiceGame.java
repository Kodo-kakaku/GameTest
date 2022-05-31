package fakeDice;

import game.Dice;

public class FakeDiceGame implements Dice {
    static int counter = 0;

    public FakeDiceGame() {
    }
    @Override
    public int roll() {
        return switch (counter++) {
            case 0, 3 -> 1;
            case 1, 2 -> 2;
            default -> 0;
        };
    }
}
