package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

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

        return character.getSkills().getSkillPoints(Skill.SKILL_SUMMON_SHOPKEEPER) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using summon shopkeeper.");

        MainCharacter character = gameWorld.getMainCharacter();
        int skillPoints = character.getSkills().getSkillPoints(Skill.SKILL_SUMMON_SHOPKEEPER);
        int priceReductionPercent = 2 * skillPoints;
        character.getStatusEffects().setStat(StatusEffectStats.STAT_PRICE_REDUCTION_PERCENT, priceReductionPercent);
        LogRendererFactory.instance().log("Summoned shopkeeper.");
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int priceReductionPercent = 2 * skillPoints;

        return new String[] {
            "Price reduction: " + priceReductionPercent + "%"
        };
    }
}
