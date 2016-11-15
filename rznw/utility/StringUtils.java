package rznw.utility;

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
}
