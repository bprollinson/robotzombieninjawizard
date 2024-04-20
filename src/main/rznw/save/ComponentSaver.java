package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedWriter;
import java.io.IOException;

public abstract class ComponentSaver
{
    public abstract void save(GameWorld gameWorld, BufferedWriter fileWriter);

    protected void writeLine(BufferedWriter fileWriter, String line)
    {
        try
        {
            fileWriter.write(line);
            fileWriter.newLine();
        }
        catch (IOException ioe)
        {
        }
    }

    protected void writeLine(BufferedWriter fileWriter, int lineValue)
    {
        this.writeLine(fileWriter, "" + lineValue);
    }
}
