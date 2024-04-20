package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class PaddingStat extends Stat
{
    public String getDisplayName()
    {
        return "Padding";
    }

    public String getDescription()
    {
        return "Reduces the damage you take.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Padding: " + 2 * statPoints + "%"
        };
    }
}
