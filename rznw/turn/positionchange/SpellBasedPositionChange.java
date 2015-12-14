package rznw.turn.positionchange;

import rznw.game.spell.Spell;

public class SpellBasedPositionChange extends PositionChange
{
    public SpellBasedPositionChange(int initialRow, int initialColumn, int direction)
    {
        this.initialRow = initialRow;
        this.initialColumn = initialColumn;

        this.deltaRow = 0;
        this.deltaColumn = 0;

        switch (direction)
        {
            case Spell.DIRECTION_UP:
                this.deltaRow = -1;
                break;
            case Spell.DIRECTION_DOWN:
                this.deltaRow = 1;
                break;
            case Spell.DIRECTION_LEFT:
                this.deltaColumn = -1;
                break;
            case Spell.DIRECTION_RIGHT:
                this.deltaColumn = 1;
                break;
        }

        this.finalRow = this.initialRow + this.deltaRow;
        this.finalColumn = this.initialColumn + this.deltaColumn;
    }
}
