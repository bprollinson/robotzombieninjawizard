package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class ComponentLoader
{
    public abstract void load(GameWorld gameWorld, BufferedReader fileReader);

    protected String readLine(BufferedReader fileReader)
    {
        try
        {
            return fileReader.readLine();
        }
        catch (IOException ioe)
        {
        }

        return "";
    }

    protected int readInteger(BufferedReader fileReader)
    {
        try
        {
            return Integer.parseInt(fileReader.readLine());
        }
        catch (IOException ioe)
        {
        }

        return 0;
    }
}
