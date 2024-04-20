package rznw.utility;

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
}
