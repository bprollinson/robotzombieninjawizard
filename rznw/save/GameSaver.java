package rznw.save;

import rznw.map.GameWorld;

import java.io.File;
import java.io.IOException;

public class GameSaver
{
    public void save(GameWorld gameWorld, int slot)
    {
        this.makeDirectory();
        this.makeSaveFile(gameWorld, slot);
    }

    private void makeDirectory()
    {
        File directory = new File("saves");
        if (!directory.exists())
        {
            directory.mkdir();
        }
    }

    private void makeSaveFile(GameWorld gameWorld, int slot)
    {
        String fileName = (slot + 1) + ".sav";
        String filePath = "saves/" + fileName;

        File file = new File(filePath);
        try
        {
            file.createNewFile();
        }
        catch (IOException ioe)
        {
        }
    }
}
