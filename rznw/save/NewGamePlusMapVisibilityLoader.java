package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class NewGamePlusMapVisibilityLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numVisibleElements = this.readInteger(fileReader);

        for (int i = 0; i < numVisibleElements; i++)
        {
            int row = this.readInteger(fileReader);
            int column = this.readInteger(fileReader);
        }
    }
}
