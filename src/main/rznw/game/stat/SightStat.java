package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class SightStat extends Stat
{
    public String getDisplayName()
    {
        return "Sight";
    }

    public String getDescription()
    {
        return "Increases your field of view.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        int viewRadius = 2 + statPoints;

        return new String[] {
            "View radius: " + viewRadius
        };
    }
}
