package net.openoil;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.fail;

public class TestUtil {

    /* Compares two BigDecimal lists using BigDecimal.compareTo()
     * instead of Object.equals(). */
    public static void assertEquals(List<BigDecimal> expected,
                                    List<BigDecimal> actual) {
        if (expected == null && actual == null) {
            return;
        } else if (expected == null || actual == null) {
            failNotEquals(expected, actual);
        }

        if (expected.size() != actual.size()) {
            failNotEquals(expected, actual);
        }

        for (int i = 0; i < expected.size(); ++i) {
            if (expected.get(i).compareTo(actual.get(i)) != 0) {
                failNotEquals(expected, actual);
            }
        }
    }

    private static void failNotEquals(Object expected, Object actual) {
        fail(format(expected, actual));
    }

    private static String format(Object expected, Object actual) {
        String formatted = "";
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        if (expectedString.equals(actualString)) {
            return formatted + "expected: "
                + formatClassAndValue(expected, expectedString)
                + " but was: " + formatClassAndValue(actual, actualString);
        } else {
            return formatted + "expected:<" + expectedString + "> but was:<"
                + actualString + ">";
        }
    }

    private static String formatClassAndValue(Object value, String valueString) {
        String className = value == null ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }

}
