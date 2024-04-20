package rznw.game.maincharacter;

import rznw.game.stat.StatFactory;

public class MainCharacterStats
{
    public static final int STAT_POINTS_PER_LEVEL = 4;
    public static final int MAX_STAT_POINTS = 20;

    private static String[] statCategory = {
        "Vitality",
        "Agility",
        "Fortitude",
        "Magic"
    };

    private int[] stats;

    public MainCharacterStats()
    {
        this.stats = new int[16];

        for (int i = 0; i < this.stats.length; i++)
        {
            this.stats[i] = 0;
        }
    }

    public static String getStatCategory(int categoryNumber)
    {
        return MainCharacterStats.statCategory[categoryNumber];
    }

    public static String getStatName(int statNumber)
    {
        return MainCharacterStats.getStatFactory().getStat(statNumber).getDisplayName();
    }

    public static String getStatDescription(int statNumber)
    {
        return MainCharacterStats.getStatFactory().getStat(statNumber).getDescription();
    }

    public void addStatPoint(int statNumber)
    {
        this.stats[statNumber]++;
    }

    public int getStatPoints(int statNumber)
    {
        return this.stats[statNumber];
    }

    public void setStatPoints(int statNumber, int points)
    {
        this.stats[statNumber] = points;
    }

    public static StatFactory getStatFactory()
    {
        return new StatFactory();
    }
}
