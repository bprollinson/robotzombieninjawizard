package rznw.turn;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.element.MapElement;

public class EnemyAIBasedPositionChange
{
    private int initialRow;
    private int initialColumn;

    private int deltaRow;
    private int deltaColumn;

    private int finalRow;
    private int finalColumn;

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

    public int getInitialRow()
    {
        return this.initialRow;
    }

    public int getInitialColumn()
    {
        return this.initialColumn;
    }

    public int getFinalRow()
    {
        return this.finalRow;
    }

    public int getFinalColumn()
    {
        return this.finalColumn;
    }

    public boolean isChange()
    {
        return this.deltaRow != 0 || this.deltaColumn != 0;
    }
}
