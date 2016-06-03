package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedWriter;

public interface ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter);
}
