package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class LifeBondStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Bonus HP: " + 5 * statPoints + "%"
        };
    }
}
