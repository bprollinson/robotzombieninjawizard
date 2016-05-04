package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class LifeBondStat extends Stat
{
    public String getDisplayName()
    {
        return "Life Bond";
    }

    public String getDescription()
    {
        return "Amplifies the strength of healing effects on you.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Bonus HP: " + 5 * statPoints + "%"
        };
    }
}
