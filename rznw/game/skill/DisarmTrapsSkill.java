package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class DisarmTrapsSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Disarm probability: " + 5 * skillPoints + "%"
        };
    }
}
