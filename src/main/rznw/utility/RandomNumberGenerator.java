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

    public static boolean rollSucceeds(int probability)
    {
        int random = RandomNumberGenerator.randomInteger(1, 100);

        return random <= probability;
    }
}
