package assertions;

public class Assertions {
    public static void assertGreaterThan(int maxValue, int verifiable) {
        if (maxValue < verifiable) {
            throw new AssertionError(
                    String.format("Checked value is greater than the maximum allowed! " +
                                    "%nChecked value: %d > Max value: %d", verifiable, maxValue));
        }
    }

    public static void assertLessThan(int minValue, int verifiable) {
        if (minValue > verifiable) {
            throw new AssertionError(
                    String.format("Check my value is less than the minimum allowed! " +
                            "%nChecked value: %d < Min value: %d", verifiable, minValue));
        }
    }

    public static void assertEquals(String firstPlayer, String secondPlayer) {
        if (!firstPlayer.equals(secondPlayer)) {
            throw new AssertionError(String.format("First player: %s != Second player: %s", firstPlayer, secondPlayer));
        }
    }
}