package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class SummonShopkeeperSkill extends Skill
{
    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkillPoints(4) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Rage");

        MainCharacter character = gameWorld.getMainCharacter();
        int skillPoints = character.getSkillPoints(4);
        int priceReductionPercent = 2 * skillPoints;
        character.getStatusEffects().enableSummonShopkeeper(priceReductionPercent);
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int priceReductionPercent = 2 * skillPoints;

        return new String[] {
            "Price reduction: " + priceReductionPercent + "%"
        };
    }
}
