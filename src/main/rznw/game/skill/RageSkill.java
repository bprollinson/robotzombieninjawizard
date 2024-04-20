package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class RageSkill extends Skill
{
    public String getDisplayName()
    {
        return "Rage";
    }

    public String getDescription()
    {
        return "Increases your damage but decreases your defense for a period of time.";
    }

    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkills().getSkillPoints(Skill.SKILL_RAGE) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using rage.");

        MainCharacter character = gameWorld.getMainCharacter();
        int numTurns = 1 + character.getSkills().getSkillPoints(Skill.SKILL_RAGE);
        character.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_RAGE, numTurns);

        LogRendererFactory.instance().log("Rage enabled.");
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int numTurns = 1 + skillPoints;
        int bonusDamagePercent = 2 * skillPoints;
        int dodgePenalty = Math.max(21 - skillPoints, 1);
        int paddingPenalty = Math.max(21 - skillPoints, 1);

        return new String[] {
            "Turns: " + numTurns,
            "Bonus damage: " + bonusDamagePercent + "%",
            "Dodge penalty: " + dodgePenalty + "%",
            "Padding penalty: " + paddingPenalty + "%"
        };
    }
}
