package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;
import java.io.IOException;

public class MainCharacterLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        try
        {
            gameWorld.generateMainCharacter(Integer.parseInt(fileReader.readLine()));
        }
        catch (IOException ioe)
        {
        }
    }
}
