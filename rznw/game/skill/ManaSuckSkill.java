package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class ManaSuckSkill extends Skill
{
    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkillPoints(13) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Mana Suck");

        int skillPoints = gameWorld.getMainCharacter().getSkillPoints(13);
        int numTurns = 2 + (int)Math.floor(skillPoints / 4);
        gameWorld.getMainCharacter().getStatusEffects().enableManaSuck(numTurns);
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
