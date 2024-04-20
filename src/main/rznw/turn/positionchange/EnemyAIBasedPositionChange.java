package rznw.turn.positionchange;

import rznw.game.Character;
import rznw.map.element.MapElement;

public class EnemyAIBasedPositionChange extends PositionChange
{
    public EnemyAIBasedPositionChange(Character character, int deltaRow, int deltaColumn)
    {
        MapElement element = character.getMapElement();

        this.initialRow = element.getRow();
        this.initialColumn = element.getColumn();

        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;

        this.finalRow = this.initialRow + this.deltaRow;
        this.finalColumn = this.initialColumn + this.deltaColumn;
    }
}
