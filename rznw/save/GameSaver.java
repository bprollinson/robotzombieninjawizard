package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaver
{
    public void save(GameWorld gameWorld, int slot)
    {
        this.makeDirectory();
        File saveFile = this.makeSaveFile(slot);
        this.saveComponentInfo(gameWorld, saveFile);
    }

    private void makeDirectory()
    {
        File directory = new File("saves");
        if (!directory.exists())
        {
            directory.mkdir();
        }
    }

    private File makeSaveFile(int slot)
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

        return file;
    }

    private void saveComponentInfo(GameWorld gameWorld, File saveFile)
    {
        BufferedWriter fileWriter = null;

        try
        {
            fileWriter = new BufferedWriter(new FileWriter(saveFile));
        }
        catch (IOException ioe)
        {
            return;
        }

        ComponentSaver[] componentSavers = this.getComponentSavers();

        for (int i = 0; i < componentSavers.length; i++)
        {
            ComponentSaver componentSaver = componentSavers[i];
            componentSaver.save(gameWorld, fileWriter);
        }

        try
        {
            fileWriter.close();
        }
        catch (IOException ioe)
        {
        }
    }

    private ComponentSaver[] getComponentSavers()
    {
        return new ComponentSaver[] {
            new MainCharacterSaver(),
            new EnemySaver(),
            new MapSaver(),
            new InventorySaver(),
            new EquipmentSaver()
        };
    }
}
