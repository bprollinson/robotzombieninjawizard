package rznw.utility;

import java.util.Vector;

public class StringUtils
{
    public String UCFirst(String s)
    {
        if (s.length() == 0)
        {
            return "";
        }

        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public String[] splitIntoLines(String s, int maxLineLength)
    {
        String remainingString = s;
        Vector<String> lines = new Vector<String>();

        while (remainingString.length() > maxLineLength)
        {
            String searchString = remainingString.substring(0, maxLineLength);
            int lastSpacePos = searchString.lastIndexOf(' ');
            String line = searchString.substring(0, lastSpacePos);
            lines.add(line);

            remainingString = remainingString.substring(lastSpacePos + 1);
        }

        lines.add(remainingString);

        return lines.toArray(new String[lines.size()]);
    }
}
