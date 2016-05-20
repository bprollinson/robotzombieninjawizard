package rznw.game.enemy.action.choice;

import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemyMovementAction;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.ShortestPathCalculator;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.path.MapPath;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

public class MovementChoice extends EnemyActionChoice
{
    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        MapPoint startPoint = new MapPoint(enemyCharacter.getMapElement().getRow(), enemyCharacter.getMapElement().getColumn());
        MapPoint endPoint = new MapPoint(character.getMapElement().getRow(), character.getMapElement().getColumn());
        ShortestPathCalculator pathCalculator = new ShortestPathCalculator(gameWorld.getMap(), false, true);
        MapPath path = pathCalculator.calculateShortestPath(startPoint, endPoint);

        System.out.println("Path: " + path);

        PathDirection firstDirection = path.getDirection(0);

        System.out.println("Direction: " + firstDirection.getDeltaRow() + ", " + firstDirection.getDeltaColumn());

        return new EnemyMovementAction(new EnemyAIBasedPositionChange(enemyCharacter, firstDirection.getDeltaRow(), firstDirection.getDeltaColumn()));
    }
}
