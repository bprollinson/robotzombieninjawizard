package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class ManaSuckSkill extends PassiveSkill
{
    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Mana generated: " + 5 * skillPoints + "% of damage"
        };
    }
}
