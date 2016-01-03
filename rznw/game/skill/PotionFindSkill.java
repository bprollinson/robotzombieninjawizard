package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class PotionFindSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Additional potion probability: " + 5 * skillPoints + "%"
        };
    }
}
