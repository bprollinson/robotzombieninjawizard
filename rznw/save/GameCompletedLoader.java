package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class GameCompletedLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader) throws LoadException
    {
        int completed = this.readInteger(fileReader);

        if (completed == 1)
        {
            throw new GameCompletedLoadException();
        }
    }
}
