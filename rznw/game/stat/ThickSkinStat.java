package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class ThickSkinStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Chance to dodge: " + 5 * statPoints + "%"
        };
    }
}
