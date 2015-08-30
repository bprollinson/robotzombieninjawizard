package rznw.turn.positionchange;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.element.MapElement;

public class EnemyAIBasedPositionChange extends PositionChange
{
    public EnemyAIBasedPositionChange(EnemyCharacter enemy, int deltaRow, int deltaColumn)
    {
        MapElement element = enemy.getMapElement();

        this.initialRow = element.getRow();
        this.initialColumn = element.getColumn();

        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;

        this.finalRow = this.initialRow + this.deltaRow;
        this.finalColumn = this.initialColumn + this.deltaColumn;
    }
}
