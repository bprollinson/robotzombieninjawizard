package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.maincharacter.inventory.Inventory;

public abstract class MainCharacter extends Character
{
    public static final int STAT_POINTS_PER_LEVEL = 5;
    public static final int SKILL_POINTS_PER_LEVEL = 5;
    public static final int SPELL_POINTS_PER_LEVEL = 5;

    private int level = 0;
    private int experience = 0;
    private int pendingLevels = 0;

    private Inventory inventory;

    public MainCharacter()
    {
        this.inventory = new Inventory();
    }

    public int getLevel()
    {
        return this.level;
    }

    public int getExperience()
    {
        return this.experience;
    }

    public int getMaxHP()
    {
        return 1000;
    }

    public int getDamage()
    {
        return 5;
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setPendingLevels(int pendingLevels)
    {
        this.pendingLevels = pendingLevels;
    }

    public int getPendingLevels()
    {
        return this.pendingLevels;
    }

    public void grantExperience(int experience)
    {
        this.experience += experience;
    }

    public abstract String getCharacterClass();
}
