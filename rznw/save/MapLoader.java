package rznw.save;

import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.MapElementFactory;

import java.io.BufferedReader;

public class MapLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int dungeonLevel = this.readInteger(fileReader);
        System.out.println("Loading map for dungeon level: " + dungeonLevel);

        gameWorld.initializeFromLoad(dungeonLevel);

        Map map = gameWorld.getMap();

        int numMapElements = this.readInteger(fileReader);
        System.out.println("Num map elements: " + numMapElements);

        gameWorld.initializeEnemyIndex();
        gameWorld.initializeSummonIndex();

        for (int i = 0; i < numMapElements; i++)
        {
            MapElement element = this.createElementFromFile(gameWorld, fileReader);
            if (element != null)
            {
                map.setElement(element.getRow(), element.getColumn(), element);
            }
        }

        int numMapBackgroundElements = this.readInteger(fileReader);
        System.out.println("Num map background elements: " + numMapBackgroundElements);

        for (int i = 0; i < numMapBackgroundElements; i++)
        {
            MapElement element = this.createElementFromFile(gameWorld, fileReader);
            if (element != null)
            {
                map.setBackgroundElement(element.getRow(), element.getColumn(), element);
            }
        }

        gameWorld.clearEnemySet();
        gameWorld.clearSummonSet();
    }

    private MapElement createElementFromFile(GameWorld gameWorld, BufferedReader fileReader)
    {
        int row = this.readInteger(fileReader);
        int column = this.readInteger(fileReader);
        int elementIndex = this.readInteger(fileReader);
        String metadata = this.readLine(fileReader);

        System.out.println(row + ", " + column);
        System.out.println(elementIndex);

        return MapElementFactory.factory(gameWorld, elementIndex, row, column, metadata);
    }
}
