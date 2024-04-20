package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class ManaStat extends Stat
{
    public String getDisplayName()
    {
        return "Mana";
    }

    public String getDescription()
    {
        return "Increases your maximum MP.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        int mp = 200 + 20 * statPoints;

        return new String[] {
            "MP: " + mp
        };
    }
}
