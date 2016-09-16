package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public abstract class Stat
{
    public static final int STAT_HEALTH = 0;
    public static final int STAT_PHYSICAL_REGENERATION = 1;
    public static final int STAT_LAST_BREATH = 2;
    public static final int STAT_LIFE_BOND = 3;
    public static final int STAT_ACCURACY = 4;
    public static final int STAT_DODGE = 5;
    public static final int STAT_SIGHT = 6;
    public static final int STAT_FIND_TRAPS = 7;
    public static final int STAT_DAMAGE = 8;
    public static final int STAT_PADDING = 9;
    public static final int STAT_UNENCUMBERANCE = 10;
    public static final int STAT_THICK_SKIN = 11;
    public static final int STAT_MANA = 12;
    public static final int STAT_MENTAL_REGENERATION = 13;
    public static final int STAT_MANA_BURN = 14;
    public static final int STAT_MAGIC_RESISTANCE = 15;

    public abstract String getDisplayName();

    public abstract String getDescription();

    public abstract String[] getStats(MainCharacter character, int statPoints);
}
