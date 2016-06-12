package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class MapLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int dungeonLevel = this.readInteger(fileReader);
        System.out.println("Loading map for dungeon level: " + dungeonLevel);

        gameWorld.initializeFromLoad(dungeonLevel);

        int numMapElements = this.readInteger(fileReader);
        System.out.println("Num map elements: " + numMapElements);

        for (int i = 0; i < numMapElements; i++)
        {
            int row = this.readInteger(fileReader);
            int column = this.readInteger(fileReader);
            String elementType = this.readLine(fileReader);

            System.out.println(row + ", " + column);
            System.out.println(elementType);
        }
    }
}
