package rznw.save;

import rznw.game.Character;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.FireElement;
import rznw.map.element.MapElement;

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
        String metadata = element.getMetadata();

        if (element instanceof FireElement)
        {
            String previousData = "";
            MapElement previousElement = ((FireElement)element).getPreviousMapElement();
            if (previousElement != null)
            {
                previousData += previousElement.getElementNumber() + "/" + this.getElementMetadata(previousElement);
            }

            metadata = "" + ((FireElement)element).getDuration() + "/" + previousData;
        }

        return metadata;
    }
}
