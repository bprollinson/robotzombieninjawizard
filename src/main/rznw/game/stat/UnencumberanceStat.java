package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class UnencumberanceStat extends Stat
{
    public String getDisplayName()
    {
        return "Unencumberance";
    }

    public String getDescription()
    {
        return "Increases the amount you can carry in your inventory.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        int numSlots = 1 + statPoints;
        int itemsPerSlot = 1 + statPoints;

        return new String[] {
            "Max num slots: " + numSlots,
            "Max items per slot: " + itemsPerSlot
        };
    }
}
