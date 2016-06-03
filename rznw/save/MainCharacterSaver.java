package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedWriter;
import java.io.IOException;

public class MainCharacterSaver implements ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        try
        {
            fileWriter.write("" + gameWorld.getMainCharacter().getLevel());
        }
        catch (IOException ioe)
        {
        }
    }
}
