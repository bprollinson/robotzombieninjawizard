package rznw.game;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.ClosestEnemiesCalculator;
import rznw.map.GameWorld;
import rznw.map.ShortestPathCalculator;
import rznw.map.element.MapElement;
import rznw.map.generator.MapPoint;
import rznw.map.generator.direction.PathDirection;
import rznw.map.generator.path.MapPath;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

import java.util.List;

public abstract class SummonedCharacter extends Character
{
    public abstract int getSummonNumber();

    public void generateMapElement(int row, int column)
    {
    }

    public int getMaxHP()
    {
        return 100;
    }

    public int getMaxMP()
    {
        return 100;
    }

    public int getDamage()
    {
        return 10;
    }

    public boolean meleeAttackHits()
    {
        return true;
    }

    public boolean dodgesAttack()
    {
        return false;
    }

    public boolean isSummon()
    {
        return true;
    }

    public EnemyAIBasedPositionChange getPositionChange(GameWorld gameWorld)
    {
        List<java.util.Map.Entry<EnemyCharacter, Double>> minDistanceList = new ClosestEnemiesCalculator().getSortedEnemiesList(gameWorld.getMap(), this.getMapElement());

        if (minDistanceList.size() == 0)
        {
            return new EnemyAIBasedPositionChange(this, 0, 0);
        }

        EnemyCharacter closestEnemy = minDistanceList.get(0).getKey();
        MapElement closestElement = closestEnemy.getMapElement();

        MapPoint startPoint = new MapPoint(this.getMapElement().getRow(), this.getMapElement().getColumn());
        MapPoint endPoint = new MapPoint(closestElement.getRow(), closestElement.getColumn());
        ShortestPathCalculator pathCalculator = new ShortestPathCalculator(gameWorld.getMap(), false, true);
        MapPath path = pathCalculator.calculateShortestPath(startPoint, endPoint, false);
        if (path == null)
        {
            path = pathCalculator.calculateShortestPath(startPoint, endPoint, true);
        }

        PathDirection firstDirection = path.getDirection(0);

        return new EnemyAIBasedPositionChange(this, firstDirection.getDeltaRow(), firstDirection.getDeltaColumn());
    }
}
