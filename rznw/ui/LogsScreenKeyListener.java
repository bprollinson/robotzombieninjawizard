package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class LogsScreenKeyListener extends StateTransitionKeyListener
{
    private LogsScreenRenderer logsScreenRenderer;

    public LogsScreenKeyListener(LogsScreenRenderer logsScreenRenderer)
    {
        this.logsScreenRenderer = logsScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void exitState(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.logsScreenRenderer.render();
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_LOGS_SCREEN;
    }
}
