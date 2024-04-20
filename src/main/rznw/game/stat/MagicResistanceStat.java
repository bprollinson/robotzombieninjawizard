package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class MagicResistanceStat extends Stat
{
    public String getDisplayName()
    {
        return "Magic Resistance";
    }

    public String getDescription()
    {
        return "Decreases the damage your opponents' spells do to you.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Damage reduction: " + 5 * statPoints + "%"
        };
    }
}
