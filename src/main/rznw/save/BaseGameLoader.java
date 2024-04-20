package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

public abstract class BaseGameLoader
{
    public void load(GameWorld gameWorld, int slot) throws LoadException
    {
        File saveFile = this.getSaveFile(slot);
        if (!saveFile.exists())
        {
            throw new MissingFileException(saveFile);
        }

        this.loadComponentInfo(gameWorld, saveFile);
    }

    protected abstract ComponentLoader[] getComponentLoaders();

    private File getSaveFile(int slot)
    {
        String fileName = (slot + 1) + ".sav";
        String filePath = "saves/" + fileName;

        return new File(filePath);
    }

    private void loadComponentInfo(GameWorld gameWorld, File saveFile) throws LoadException
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

        ComponentLoader[] componentLoaders = this.getComponentLoaders();

        for (int i = 0; i < componentLoaders.length; i++)
        {
            ComponentLoader componentLoader = componentLoaders[i];

            try
            {
                componentLoader.load(gameWorld, fileReader);
            }
            catch (LoadException le)
            {
                try
                {
                    fileReader.close();
                }
                catch (IOException ioe)
                {
                }

                throw le;
            }
        }

        try
        {
            fileReader.close();
        }
        catch (IOException ioe)
        {
        }
    }
}
