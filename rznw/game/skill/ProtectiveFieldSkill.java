package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class ProtectiveFieldSkill extends PassiveSkill
{
    public String getDisplayName()
    {
        return "Protective Field";
    }

    public String getDescription()
    {
        return "Grants you additional chance of avoiding magic damage.";
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Dodge probability: " + 5 * skillPoints + "%"
        };
    }
}
