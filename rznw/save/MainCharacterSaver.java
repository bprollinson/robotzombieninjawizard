package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.io.BufferedWriter;
import java.io.IOException;

public class MainCharacterSaver implements ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        try
        {
            fileWriter.write("" + mainCharacter.getLevel());
            fileWriter.newLine();
            fileWriter.write("" + mainCharacter.getExperience());
        }
        catch (IOException ioe)
        {
        }
    }
}
