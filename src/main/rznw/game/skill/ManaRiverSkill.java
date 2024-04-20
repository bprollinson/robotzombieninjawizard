package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class ManaRiverSkill extends PassiveSkill
{
    public String getDisplayName()
    {
        return "Mana River";
    }

    public String getDescription()
    {
        return "Provides you with a periodic chance to refill your MP.";
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        String numStepsDisplay = "N/A";

        if (skillPoints > 0)
        {
            int numSteps = Math.max(1, 20 - skillPoints);
            numStepsDisplay = "" + numSteps;
        }

        return new String[] {
            "MP recovered: 100%",
            "Steps for recovery: " + numStepsDisplay,
            "Success probability: " + skillPoints + "%"
        };
    }
}
