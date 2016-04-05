package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class MagicResistanceStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Damage reduction: " + 5 * statPoints + "%"
        };
    }
}
