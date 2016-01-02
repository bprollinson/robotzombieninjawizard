package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class PaddingStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Padding: " + 2 * statPoints + "%"
        };
    }
}
