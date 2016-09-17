package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class SummonShopkeeperSkill extends Skill
{
    public String getDisplayName()
    {
        return "Summon Shopkeeper";
    }

    public String getDescription()
    {
        return "Summons a shopkeeper who buys and sells goods.";
    }

    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkillPoints(Skill.SKILL_SUMMON_SHOPKEEPER) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Rage");

        MainCharacter character = gameWorld.getMainCharacter();
        int skillPoints = character.getSkillPoints(Skill.SKILL_SUMMON_SHOPKEEPER);
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
