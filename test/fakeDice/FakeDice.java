package fakeDice;

import game.Dice;

public class FakeDice implements Dice {
    private final int value;

    public FakeDice(int fakeValue) {
        this.value = fakeValue;
    }

    @Override
    public int roll() {
        return this.value;
    }
}
