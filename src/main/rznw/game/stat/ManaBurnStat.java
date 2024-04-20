package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class ManaBurnStat extends Stat
{
    public String getDisplayName()
    {
        return "Mana Burn";
    }

    public String getDescription()
    {
        return "Increases the damage you deal with your spells.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Bonus damage: " + 5 * statPoints + "%"
        };
    }
}
