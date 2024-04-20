package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class NewGamePlusGameCompletedLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        this.readInteger(fileReader);
    }
}
