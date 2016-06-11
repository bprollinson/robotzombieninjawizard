package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class MapLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        gameWorld.initializeFromLoad(1);
    }
}
