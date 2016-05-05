package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;

public class PotionFindSkill extends PassiveSkill
{
    public String getDisplayName()
    {
        return "Potion Find";
    }

    public String getDescription()
    {
        return "Increases your chance of finding potions from enemies.";
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        return new String[] {
            "Additional potion probability: " + 5 * skillPoints + "%"
        };
    }
}
