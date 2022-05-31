import fakeDice.FakeDice;
import assertions.Assertions;
import fakeDice.FakeDiceGame;
import fakeGameWinnerPrinter.FakeGameWinnerPrinter;
import game.*;

public class DiceGameTest {
    /*
        Заготовка для ДЗ представляет собой игру в кости.
        При вызове game.playGame(), два игрока кидают кости.
        Выигрывает игрок, у кого результат (1-6) больше

        Написать тесты (минимум 4) на классы DiceImpl и Game.
        Тесты должны найти не менее двух ошибок.

        Информацию о пройденном тесте предлагается выводить в System.out, а об упавшем в System.err
     */

    public static final String ANSI_GREEN = "\u001B[32m";

    public static void throwMoreThanSixTest(int throwsNumber) {
        final String testName = "Test name: Roll more than six with one die.";
        final int DICE_MAX_VALUE = 6;
        Dice dice = new DiceImpl();
        try {
            while(throwsNumber > 0) {
                int rollResult = dice.roll();
                Assertions.assertGreaterThan(DICE_MAX_VALUE, rollResult);
                throwsNumber--;
            }
            System.out.printf(ANSI_GREEN + "%s%nTest passed.%n%n", testName);
        } catch (Throwable e) {
            System.err.printf("%s%nFail: %s%n%n", testName, e.getMessage());
        }
    }

    public static void throwLessThanOneTest(int throwsNumber) {
        final String testName = "Test name: Roll less than one with one die.";
        final int DICE_MIN_VALUE = 1;
        Dice dice = new DiceImpl();
        try {
            while(throwsNumber > 0) {
                int rollResult = dice.roll();
                Assertions.assertLessThan(DICE_MIN_VALUE, rollResult);
                throwsNumber--;
            }
            System.out.printf(ANSI_GREEN + "%s%nTest passed.%n%n", testName);
        } catch (Throwable e) {
            System.err.printf("%s%nFail: %s%n%n", testName, e.getMessage());
        }
    }

    public static void gameDrawTest(int numberOnCube) {
        final String testName = "Test name: Verification of victory. " +
                "In case of a draw, the winner cannot be determined!";
        // first game: Вася vs Игорь
        Dice dice = new FakeDice(numberOnCube);
        FakeGameWinnerPrinter firstWinnerPrinter = new FakeGameWinnerPrinter();
        Game game = new Game(dice, firstWinnerPrinter);
        game.playGame(new Player("Вася"), new Player("Игорь"));

        // second game: Игорь vs Вася
        FakeGameWinnerPrinter secondWinnerPrinter = new FakeGameWinnerPrinter();
        game = new Game(dice, secondWinnerPrinter);
        game.playGame(new Player("Игорь"), new Player("Вася"));
        try {
            Assertions.assertEquals(
                    firstWinnerPrinter.getPrintedWinner(),
                    secondWinnerPrinter.getPrintedWinner());
            System.out.printf(ANSI_GREEN + "%s%nTest passed.%n", testName);
        } catch (Throwable e) {
            System.err.printf("%s%nFail, logic error: %s%n%n", testName, e.getMessage());
        }
    }

    public static void gamePlayTest() {
        final String firstPlayTest  = "Test name: Checking Igor's victory over Vasily";
        final String secondPlayTest = "Test name: Checking Vasily's victory over Igor";
        final String playerOne = "Вася";
        final String playerTwo = "Игорь";

        Dice dice = new FakeDiceGame();
        FakeGameWinnerPrinter winnerPrinter = new FakeGameWinnerPrinter();
        Game game = new Game(dice, winnerPrinter);
        game.playGame(new Player(playerOne), new Player(playerTwo));

        try {
            Assertions.assertEquals(playerTwo, winnerPrinter.getPrintedWinner());
            System.out.printf(ANSI_GREEN + "%s%nTest passed.%n%n", firstPlayTest);
        } catch (Throwable e) {
            System.err.printf("%s%nFail, logic error: %s%n%n", firstPlayTest, e.getMessage());
        }

        game = new Game(dice, winnerPrinter);
        game.playGame(new Player(playerOne), new Player(playerTwo));

        try {
            Assertions.assertEquals(playerOne, winnerPrinter.getPrintedWinner());
            System.out.printf(ANSI_GREEN + "%s%nTest passed.%n%n", secondPlayTest);
        } catch (Throwable e) {
            System.err.printf("%s%nFail, logic error: %s%n%n", secondPlayTest, e.getMessage());
        }
    }

    public static void main(String[] args) {
        throwMoreThanSixTest(100);
        throwLessThanOneTest(100);
        gameDrawTest(1);
        gamePlayTest();
    }
}
