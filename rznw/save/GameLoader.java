package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

public class GameLoader
{
    public void load(GameWorld gameWorld, int slot)
    {
        File saveFile = this.getSaveFile(slot);

        this.loadComponentInfo(gameWorld, saveFile);
    }

    private File getSaveFile(int slot)
    {
        String fileName = (slot + 1) + ".sav";
        String filePath = "saves/" + fileName;

        return new File(filePath);
    }

    private void loadComponentInfo(GameWorld gameWorld, File saveFile)
    {
        BufferedReader fileReader = null;

        try
        {
            fileReader = new BufferedReader(new FileReader(saveFile));
        }
        catch (FileNotFoundException fnfe)
        {
            return;
        }

        try
        {
            gameWorld.generateMainCharacter(Integer.parseInt(fileReader.readLine()));
        }
        catch (IOException ioe)
        {
        }
        gameWorld.initializeFromLoad(1);

        try
        {
            fileReader.close();
        }
        catch (IOException ioe)
        {
        }
    }
}
