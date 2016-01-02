package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class AccuracyStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        int toHitPercent = 50 + 2 * statPoints;

        return new String[] {
            "To hit: " + toHitPercent + "%"
        };
    }
}
