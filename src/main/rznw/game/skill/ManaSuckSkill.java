package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class ManaSuckSkill extends Skill
{
    public String getDisplayName()
    {
        return "Mana Suck";
    }

    public String getDescription()
    {
        return "You receive MP from damage you receive.";
    }

    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkills().getSkillPoints(Skill.SKILL_MANA_SUCK) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        LogRendererFactory.instance().log("Using mana suck.");

        int skillPoints = gameWorld.getMainCharacter().getSkills().getSkillPoints(Skill.SKILL_MANA_SUCK);
        int numTurns = 2 + (int)Math.floor(skillPoints / 4);
        gameWorld.getMainCharacter().getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_MANA_SUCK, numTurns);

        LogRendererFactory.instance().log("Mana suck enabled.");
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int numTurns = 2 + (int)Math.floor(skillPoints / 4);

        return new String[] {
            "Mana generated: " + 5 * skillPoints + "% of damage",
            "Turns: " + numTurns
        };
    }
}
