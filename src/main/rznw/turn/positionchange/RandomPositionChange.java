package rznw.turn.positionchange;

import rznw.game.Character;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class RandomPositionChange extends PositionChange
{
    public RandomPositionChange(Character character)
    {
        MapElement mapElement = character.getMapElement();

        this.initialRow = mapElement.getRow();
        this.initialColumn = mapElement.getColumn();

        this.calculateDeltas();

        this.finalRow = this.initialRow + this.deltaRow;
        this.finalColumn = this.initialColumn + this.deltaColumn;
    }

    private void calculateDeltas()
    {
        int randomPosition = RandomNumberGenerator.randomInteger(0, 7);

        switch (randomPosition)
        {
            case 0:
                this.deltaRow = -1;
                this.deltaColumn = -1;
                break;
            case 1:
                this.deltaRow = -1;
                this.deltaColumn = 0;
                break;
            case 2:
                this.deltaRow = -1;
                this.deltaColumn = -1;
                break;
            case 3:
                this.deltaRow = 0;
                this.deltaColumn = -1;
                break;
            case 4:
                this.deltaRow = 0;
                this.deltaColumn = 1;
                break;
            case 5:
                this.deltaRow = 1;
                this.deltaColumn = -1;
                break;
            case 6:
                this.deltaRow = 1;
                this.deltaColumn = 0;
                break;
            case 7:
                this.deltaRow = 1;
                this.deltaColumn = 1;
                break;
        }
    }
}
