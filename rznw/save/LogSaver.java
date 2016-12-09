package rznw.save;

import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

import java.io.BufferedWriter;
import java.util.Vector;

class LogSaver extends ComponentSaver
{
    public void save(GameWorld gameWorld, BufferedWriter fileWriter)
    {
        Vector<String> logLines = LogRendererFactory.instance().getLines();

        this.writeLine(fileWriter, logLines.size());

        for (int i = 0; i < logLines.size(); i++)
        {
            this.writeLine(fileWriter, logLines.get(i));
        }
    }
}
