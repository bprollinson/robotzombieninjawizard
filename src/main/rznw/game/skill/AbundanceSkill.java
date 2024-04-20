package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class AbundanceSkill extends PassiveSkill
{
    public String getDisplayName()
    {
        return "Abundance";
    }

    public String getDescription()
    {
        return "Increases the amount of gold you receive from enemies.";
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Bonus gold: 0 - " + skillPoints + "%"
        };
    }
}
