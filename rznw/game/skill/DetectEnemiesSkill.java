package rznw.game.skill;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class DetectEnemiesSkill extends Skill
{
    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        return character.getSkillPoints(1) > 0;
    }

    public void use(GameWorld gameWorld)
    {
        System.out.println("Using Detect Enemies");

        int successProbability = 20 + 5 * gameWorld.getMainCharacter().getSkillPoints(5);
        int randomNumber = RandomNumberGenerator.randomInteger(1, 100);
        if (randomNumber > successProbability)
        {
            System.out.println("Failure");
            return;
        }

        System.out.println("Success");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();

        int maxDistance = 2 * character.getSkillPoints(1);

        Map map = gameWorld.getMap();

        Collection<EnemyCharacter> enemies = map.getEnemies();
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            MapElement enemyElement = enemy.getMapElement();

            double distance = Math.sqrt(Math.pow(enemyElement.getRow() - characterElement.getRow(), 2) + Math.pow(enemyElement.getColumn() - characterElement.getColumn(), 2));

            if (distance <= maxDistance)
            {
                for (int row = enemyElement.getRow() - 1; row <= enemyElement.getRow() + 1; row++)
                {
                    for (int column = enemyElement.getColumn() - 1; column <= enemyElement.getColumn() + 1; column++)
                    {
                        map.setVisible(character, row, column);
                    }
                }
            }
        }
    }
}
