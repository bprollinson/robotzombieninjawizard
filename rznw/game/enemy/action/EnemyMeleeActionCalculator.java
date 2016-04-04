package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.path.MapPath;
import rznw.map.ShortestPathCalculator;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

public class EnemyMeleeActionCalculator extends EnemyActionCalculator
{
    public EnemyAIBasedPositionChange getPositionChange(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        EnemyAIBasedPositionChange confusionPositionChange = this.getConfusionPositionChange(gameWorld, enemyCharacter);
        if (confusionPositionChange != null)
        {
            return confusionPositionChange;
        }

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
}
