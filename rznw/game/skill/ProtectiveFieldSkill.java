package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class ProtectiveFieldSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Dodge probability: " + 5 * skillPoints + "%"
        };
    }
}
