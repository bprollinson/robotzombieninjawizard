package rznw.game.skill;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.MapElement;
import rznw.map.element.Stairs;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.utility.RandomNumberGenerator;

public class FindStairsSkill extends Skill
{
    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkillPoints(7) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Find Stairs");

        MainCharacter character = gameWorld.getMainCharacter();
        int successProbability = 20 + 5 * character.getSkillPoints(7);
        int randomNumber = RandomNumberGenerator.randomInteger(1, 100);
        if (randomNumber > successProbability)
        {
            System.out.println("Failure");
            return;
        }

        System.out.println("Success");

        Map map = gameWorld.getMap();
        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getBackgroundElement(row, column);
                if (element instanceof Stairs)
                {
                    System.out.println("Found the stairs!");
                    map.setVisible(character, row, column);
                }
            }
        }
    }
}
