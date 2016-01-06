package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class DetectVitalitySkill extends Skill
{
    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkillPoints(0) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Detect Vitality");

        MainCharacter character = gameWorld.getMainCharacter();
        int skillPoints = character.getSkillPoints(0);
        int radius = 1 + skillPoints;
        character.getStatusEffects().enableDetectVitality(skillPoints);
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int radius = 1 + skillPoints;

        return new String[] {
            "Radius: " + radius
        };
    }
}
