package rznw.game.statuseffects;

import java.util.HashMap;

public class StatusEffectStats
{
    public static final int NUM_STATS = 9;

    public static final int STAT_ARMOR_BREAK = 0;
    public static final int STAT_DETECT_VITALITY_RADIUS = 1;
    public static final int STAT_ITEM_TRADE_NUMBER = 2;
    public static final int STAT_PRICE_REDUCTION_PERCENT = 3;
    public static final int STAT_BONUS_DROP_PROBABILITY = 4;
    public static final int STAT_BONUS_GOLD_PERCENT = 5;
    public static final int STAT_MEAT_SHIELD_PADDING_PERCENT = 6;
    public static final int STAT_MEAT_SHIELD_DODGE_PERCENT = 7;
    public static final int STAT_CRUSHER_RAGE_PERCENT = 8;

    private HashMap<Integer, Integer> otherStats = new HashMap<Integer, Integer>();

    public StatusEffectStats()
    {
        for (int i = 0; i < StatusEffectStats.NUM_STATS; i++)
        {
            this.otherStats.put(i, 0);
        }
    }

    public int getStat(int index)
    {
        return this.otherStats.get(index);
    }

    public void setStat(int index, int value)
    {
        this.otherStats.put(index, value);
    }
}
