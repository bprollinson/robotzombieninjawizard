package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class DetectVitalitySkill extends Skill
{
    public String getDisplayName()
    {
        return "Detect Vitality";
    }

    public String getDescription()
    {
        return "Detects enemy HP.";
    }

    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkills().getSkillPoints(Skill.SKILL_DETECT_VITALITY) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using detect vitality.");

        MainCharacter character = gameWorld.getMainCharacter();
        int skillPoints = character.getSkills().getSkillPoints(Skill.SKILL_DETECT_VITALITY);
        int radius = 1 + skillPoints;
        character.getStatusEffects().setStat(StatusEffectStats.STAT_DETECT_VITALITY_RADIUS, skillPoints);
        LogRendererFactory.instance().log("Detected enemy vitality.");
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int radius = 1 + skillPoints;

        return new String[] {
            "Radius: " + radius
        };
    }
}
