package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class Skill
{
    public static final int SKILL_DETECT_VITALITY = 0;
    public static final int SKILL_DETECT_ENEMIES = 1;
    public static final int SKILL_BLOOD_RAGE = 2;
    public static final int SKILL_POTION_FIND = 3;
    public static final int SKILL_SUMMON_SHOPKEEPER = 4;
    public static final int SKILL_WAYPOINT = 5;
    public static final int SKILL_FAST_HANDS = 6;
    public static final int SKILL_FIND_STAIRS = 7;
    public static final int SKILL_ITEM_TRADE = 8;
    public static final int SKILL_RAGE = 9;
    public static final int SKILL_ABUNDANCE = 10;
    public static final int SKILL_DISARM_TRAPS = 11;
    public static final int SKILL_MAGIC_SEEDS = 12;
    public static final int SKILL_MANA_SUCK = 13;
    public static final int SKILL_PROTECTIVE_FIELD = 14;
    public static final int SKILL_MANA_RIVER = 15;

    public abstract String getDisplayName();

    public abstract String getDescription();

    public abstract boolean canUse(GameWorld gameWorld);

    public abstract void use(GameWorld gameWorld);

    public abstract String[] getStats(MainCharacter character, int skillPoints);
}
