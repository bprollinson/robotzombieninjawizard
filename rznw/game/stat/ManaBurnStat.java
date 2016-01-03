package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class ManaBurnStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Bonus damage: " + 5 * statPoints + "%"
        };
    }
}
