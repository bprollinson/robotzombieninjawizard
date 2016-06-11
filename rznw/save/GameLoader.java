package rznw.save;

import rznw.map.GameWorld;

public class GameLoader
{
    public void load(GameWorld gameWorld, int slot)
    {
        gameWorld.initializeFromLoad(1);
    }
}
