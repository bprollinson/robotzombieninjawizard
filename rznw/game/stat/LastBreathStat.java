package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class LastBreathStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Revival probability: " + 5 * statPoints + "%"
        };
    }
}
