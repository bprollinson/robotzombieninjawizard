package rznw.utility;

import java.util.Random;

public class RandomNumberGenerator
{
    public static int randomInteger(int minValue, int maxValue)
    {
        Random random = new Random();
        int offset = random.nextInt(maxValue - minValue + 1);

        return minValue + offset;
    }
}
