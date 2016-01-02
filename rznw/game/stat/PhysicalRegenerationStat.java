package rznw.game.stat;

import rznw.game.maincharacter.MainCharacter;

public class PhysicalRegenerationStat extends Stat
{
    public String[] getStats(MainCharacter character, int statPoints)
    {
        String numStepsDisplay = "N/A";

        if (statPoints > 0)
        {
            int numSteps = Math.max(1, 20 - statPoints);
            numStepsDisplay = "" + numSteps;
        }

        return new String[] {
            "HP healed: 10",
            "Steps for heal: " + numStepsDisplay
        };
    }
}
