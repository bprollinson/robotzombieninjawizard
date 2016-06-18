package rznw.save;

import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

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

        for (int i = 0; i < numMapElements; i++)
        {
            int row = this.readInteger(fileReader);
            int column = this.readInteger(fileReader);
            int elementIndex = this.readInteger(fileReader);

            System.out.println(row + ", " + column);
            System.out.println(elementIndex);

            MapElement element = MapElementFactory.factory(gameWorld, elementIndex, row, column);
            if (element != null)
            {
                map.setElement(row, column, element);
            }
        }

        int numMapBackgroundElements = this.readInteger(fileReader);
        System.out.println("Num map background elements: " + numMapBackgroundElements);

        for (int i = 0; i < numMapBackgroundElements; i++)
        {
            int row = this.readInteger(fileReader);
            int column = this.readInteger(fileReader);
            int elementIndex = this.readInteger(fileReader);

            System.out.println(row + ", " + column);
            System.out.println(elementIndex);

            MapElement element = MapElementFactory.factory(gameWorld, elementIndex, row, column);
            if (element != null)
            {
                map.setBackgroundElement(row, column, element);
            }
        }
    }
}
