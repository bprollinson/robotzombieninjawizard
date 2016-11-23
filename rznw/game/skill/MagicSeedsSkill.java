package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class MagicSeedsSkill extends Skill
{
    public String getDisplayName()
    {
        return "Magic Seeds";
    }

    public String getDescription()
    {
        return "Increases your effective spell levels.";
    }

    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkills().getSkillPoints(Skill.SKILL_MAGIC_SEEDS) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using magic seeds.");

        int skillPoints = gameWorld.getMainCharacter().getSkills().getSkillPoints(Skill.SKILL_MAGIC_SEEDS);
        int numTurns = 2 + (int)Math.floor(skillPoints / 4);
        gameWorld.getMainCharacter().getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MAGIC_SEEDS, numTurns);

        LogRendererFactory.instance().log("Magic seeds enabled.");
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int bonusSpellLevels = (int)Math.floor(skillPoints / 4);
        int numTurns = 2 + (int)Math.floor(skillPoints / 4);

        return new String[] {
            "Bonus spell levels: " + bonusSpellLevels,
            "Turns: " + numTurns
        };
    }
}
