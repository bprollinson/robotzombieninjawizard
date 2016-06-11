package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public abstract class ComponentLoader
{
    public abstract void load(GameWorld gameWorld, BufferedReader fileReader);
}
