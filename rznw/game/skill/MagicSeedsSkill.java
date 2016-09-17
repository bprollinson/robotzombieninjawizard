package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

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

        return character.getSkillPoints(Skill.SKILL_MAGIC_SEEDS) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Magic Seeds");

        int skillPoints = gameWorld.getMainCharacter().getSkillPoints(Skill.SKILL_MAGIC_SEEDS);
        int numTurns = 2 + (int)Math.floor(skillPoints / 4);
        gameWorld.getMainCharacter().getStatusEffects().enableMagicSeeds(numTurns);
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
