package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class SightStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        int viewRadius = 2 + statPoints;

        return new String[] {
            "View radius: " + viewRadius
        };
    }
}
