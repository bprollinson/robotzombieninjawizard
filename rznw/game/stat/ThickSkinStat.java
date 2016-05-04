package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class ThickSkinStat extends Stat
{
    public String getDisplayName()
    {
        return "Thick Skin";
    }

    public String getDescription()
    {
        return "Increases your probability of dodging status effects.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Chance to dodge: " + 5 * statPoints + "%"
        };
    }
}
