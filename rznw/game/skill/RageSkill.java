package rznw.game.skill;

import rznw.game.StatusEffects;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

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

        return character.getSkillPoints(Skill.SKILL_RAGE) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Rage");

        MainCharacter character = gameWorld.getMainCharacter();
        int numTurns = 1 + character.getSkillPoints(Skill.SKILL_RAGE);
        System.out.println("Rage turns: " + numTurns);
        character.getStatusEffects().setStatusEffectTurns(StatusEffects.EFFECT_RAGE, numTurns);
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
