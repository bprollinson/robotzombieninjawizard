package rznw.ui;

import java.awt.event.KeyEvent;

public class LoadScreenKeyListener extends StateTransitionKeyListener
{
    private LoadScreenRenderer loadScreenRenderer;

    public LoadScreenKeyListener(LoadScreenRenderer loadScreenRenderer)
    {
        this.loadScreenRenderer = loadScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.loadScreenRenderer.render();
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_LOAD_SCREEN;
    }
}
