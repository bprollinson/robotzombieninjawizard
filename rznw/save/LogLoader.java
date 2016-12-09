package rznw.save;

import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

import java.io.BufferedReader;

class LogLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numLogLines = this.readInteger(fileReader);

        for (int i = 0; i < numLogLines; i++)
        {
            String logLine = this.readLine(fileReader);
            LogRendererFactory.instance().log(logLine, false);
        }
    }
}
