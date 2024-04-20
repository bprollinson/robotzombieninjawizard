package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class LastBreathStat extends Stat
{
    public String getDisplayName()
    {
        return "Last Breath";
    }

    public String getDescription()
    {
        return "Increases your chance to recover with 1 HP when dying.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Revival probability: " + 5 * statPoints + "%"
        };
    }
}
