package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class GameCompletedLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        this.readInteger(fileReader);
    }
}
