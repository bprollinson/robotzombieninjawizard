package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class NewGamePlusMapLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int dungeonLevel = this.readInteger(fileReader);
        int numMapElements = this.readInteger(fileReader);

        for (int i = 0; i < numMapElements; i++)
        {
            this.createElementFromFile(gameWorld, fileReader);
        }

        int numMapBackgroundElements = this.readInteger(fileReader);

        for (int i = 0; i < numMapBackgroundElements; i++)
        {
            this.createElementFromFile(gameWorld, fileReader);
        }

        gameWorld.initializeToDefaultStateAfterLoad();
    }

    private void createElementFromFile(GameWorld gameWorld, BufferedReader fileReader)
    {
        int row = this.readInteger(fileReader);
        int column = this.readInteger(fileReader);
        int elementIndex = this.readInteger(fileReader);
        String metadata = this.readLine(fileReader);
    }
}
