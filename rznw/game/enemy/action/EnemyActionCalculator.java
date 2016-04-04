package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.ShortestPathCalculator;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.path.MapPath;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;
import rznw.utility.RandomNumberGenerator;

public abstract class EnemyActionCalculator
{
    public abstract EnemyAIBasedPositionChange getPositionChange(GameWorld gameWorld, EnemyCharacter enemyCharacter);

    protected EnemyAIBasedPositionChange getConfusionPositionChange(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        if (enemyCharacter.getStatusEffects().isConfused() || enemyCharacter.distanceFromMainCharacter(gameWorld) > enemyCharacter.getViewRadius())
        {
            if (enemyCharacter.getStatusEffects().isConfused())
            {
                System.out.println("Enemy is confused!");
            }

            return this.getRandomPositionChange(enemyCharacter);
        }

        return null;
    }

    protected EnemyAIBasedPositionChange getPathBasedPositionChange(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        MapPoint startPoint = new MapPoint(enemyCharacter.getMapElement().getColumn(), enemyCharacter.getMapElement().getRow());
        MapPoint endPoint = new MapPoint(character.getMapElement().getColumn(), character.getMapElement().getRow());
        ShortestPathCalculator pathCalculator = new ShortestPathCalculator(gameWorld.getMap(), false, true);
        MapPath path = pathCalculator.calculateShortestPath(startPoint, endPoint);

        System.out.println("Path: " + path);

        PathDirection firstDirection = path.getDirection(0);

        System.out.println("Direction: " + firstDirection.getDeltaY() + ", " + firstDirection.getDeltaX());

        return new EnemyAIBasedPositionChange(enemyCharacter, firstDirection.getDeltaY(), firstDirection.getDeltaX());
    }

    private EnemyAIBasedPositionChange getRandomPositionChange(EnemyCharacter enemyCharacter)
    {
        EnemyAIBasedPositionChange possibleChanges[] = {
           new EnemyAIBasedPositionChange(enemyCharacter, -1, -1),
           new EnemyAIBasedPositionChange(enemyCharacter, -1, 0),
           new EnemyAIBasedPositionChange(enemyCharacter, -1, 1),
           new EnemyAIBasedPositionChange(enemyCharacter, 0, -1),
           new EnemyAIBasedPositionChange(enemyCharacter, 0, 1),
           new EnemyAIBasedPositionChange(enemyCharacter, 1, -1),
           new EnemyAIBasedPositionChange(enemyCharacter, 1, 0),
           new EnemyAIBasedPositionChange(enemyCharacter, 1, 1)
        };

        int randomPosition = RandomNumberGenerator.randomInteger(0, possibleChanges.length - 1);
        return possibleChanges[randomPosition];
    }
}
