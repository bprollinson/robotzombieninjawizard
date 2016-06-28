package rznw.save;

import rznw.game.Character;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.DisappearingEnemyMapElement;
import rznw.map.element.FireElement;
import rznw.map.element.MapElement;
import rznw.map.element.RockWall;
import rznw.map.element.TrapMapElement;

import java.io.BufferedWriter;

public class MapSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        Map map = gameWorld.getMap();

        this.writeLine(fileWriter, map.getLevel());

        this.writeLine(fileWriter, this.getNumMapElements(map));

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);

                if (element != null)
                {
                    this.writeLine(fileWriter, row);
                    this.writeLine(fileWriter, column);
                    this.writeLine(fileWriter, element.getElementNumber());
                    this.writeLine(fileWriter, this.getElementMetadata(element));
                }
            }
        }

        this.writeLine(fileWriter, this.getNumMapBackgroundElements(map));

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getBackgroundElement(row, column);

                if (element != null)
                {
                    this.writeLine(fileWriter, row);
                    this.writeLine(fileWriter, column);
                    this.writeLine(fileWriter, element.getElementNumber());
                    this.writeLine(fileWriter, this.getElementMetadata(element));
                }
            }
        }
    }

    private int getNumMapElements(Map map)
    {
        int numElements = 0;

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                if (map.getElement(row, column) != null)
                {
                    numElements++;
                }
            }
        }

        return numElements;
    }

    private int getNumMapBackgroundElements(Map map)
    {
        int numElements = 0;

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                if (map.getBackgroundElement(row, column) != null)
                {
                    numElements++;
                }
            }
        }

        return numElements;
    }

    private String getElementMetadata(MapElement element)
    {
        String metadata = "";

        if (element instanceof RockWall)
        {
            metadata = "" + ((RockWall)element).getHP();
        }

        if (element instanceof TrapMapElement)
        {
            TrapMapElement trapMapElement = (TrapMapElement)element;
            String isSprungDisplay = trapMapElement.isSprung() ? "1" : "0";
            String isFoundDisplay = trapMapElement.isFound() ? "1" : "0";

            metadata = isSprungDisplay + "," + isFoundDisplay;
        }

        if (element instanceof DisappearingEnemyMapElement)
        {
            Character enemyCharacter = ((DisappearingEnemyMapElement)element).getCharacter();

            metadata = "" + enemyCharacter.getStatusEffects().getInvisibilityTurns();
        }

        if (element instanceof FireElement)
        {
            metadata = "" + ((FireElement)element).getDuration();
        }

        return metadata;
    }
}
