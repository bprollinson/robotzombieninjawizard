package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class FindTrapsStat extends Stat
{
    public String getDisplayName()
    {
        return "Find Traps";
    }

    public String getDescription()
    {
        return "Increases your ability of finding hidden traps.";
    }

    public String[] getStats(MainCharacter character, int statPoints)
    {
        return new String[] {
            "Trap reveal probability: " + 5 * statPoints + "%"
        };
    }
}
