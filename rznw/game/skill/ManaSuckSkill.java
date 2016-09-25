package rznw.game.skill;

import rznw.game.StatusEffects;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

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
        System.out.println("Using Mana Suck");

        int skillPoints = gameWorld.getMainCharacter().getSkills().getSkillPoints(Skill.SKILL_MANA_SUCK);
        int numTurns = 2 + (int)Math.floor(skillPoints / 4);
        gameWorld.getMainCharacter().getStatusEffects().setStatusEffectTurns(StatusEffects.EFFECT_MANA_SUCK, numTurns);
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
