package rznw.utility;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class StringUtilsTest
{
    @Test
    public void testUCFirstCapitalizesNonEmptyString()
    {
        StringUtils stringUtils = new StringUtils();

        assertEquals("One two", stringUtils.UCFirst("one two"));
    }

    @Test
    public void testUCFirstHandlesEmptyString()
    {
        StringUtils stringUtils = new StringUtils();

        assertEquals("", stringUtils.UCFirst(""));
    }

    @Test
    public void testSplitIntoLinesSplitsIntoTwoLines()
    {
        StringUtils stringUtils = new StringUtils();

        String[] expectedLines = new String[]
        {
            "1234 1234",
            "1234 1234"
        };

        assertArrayEquals(expectedLines, stringUtils.splitIntoLines("1234 1234 1234 1234", 10));
    }

    @Test
    public void testSplitIntoLinesDoesNotSplitShortString()
    {
        StringUtils stringUtils = new StringUtils();

        String[] expectedLines = new String[]
        {
            "1234 1234"
        };

        assertArrayEquals(expectedLines, stringUtils.splitIntoLines("1234 1234", 10));
    }

    @Test
    public void testSplitIntoLinesHandlesEmptyString()
    {
        StringUtils stringUtils = new StringUtils();

        String[] expectedLines = new String[]
        {
            ""
        };

        assertArrayEquals(expectedLines, stringUtils.splitIntoLines("", 10));
    }

    @Test
    public void testSplitIntoLinesSplitsIntoManyLines()
    {
        StringUtils stringUtils = new StringUtils();

        String[] expectedLines = new String[]
        {
            "1234",
            "1234",
            "1234",
            "1234"
        };

        assertArrayEquals(expectedLines, stringUtils.splitIntoLines("1234 1234 1234 1234", 5));
    }

    @Test
    public void testSplitIntoLinesHandlesSpaces()
    {
        StringUtils stringUtils = new StringUtils();

        String[] expectedLines = new String[]
        {
            "         ",
            "         "
        };

        assertArrayEquals(expectedLines, stringUtils.splitIntoLines("                   ", 10));
    }

    @Test
    public void testSplitIntoLinesHandlesEndOfStringBoundary()
    {
        StringUtils stringUtils = new StringUtils();

        String[] expectedLines = new String[]
        {
            "1234 1234",
            "1234 1234"
        };

        assertArrayEquals(expectedLines, stringUtils.splitIntoLines("1234 1234 1234 1234", 9));
    }
}
