package fakeGameWinnerPrinter;

import game.GameWinnerPrinter;
import game.Player;

public class FakeGameWinnerPrinter implements GameWinnerPrinter {

    private Player winner;

    public FakeGameWinnerPrinter() {

    }

    public String getPrintedWinner() {
        return this.winner.getName();
    }
    @Override
    public void printWinner(Player winner) {
        this.winner = winner;
    }
}
