package rznw.ui;

import java.awt.event.KeyEvent;

public class StartScreenKeyListener extends StateTransitionKeyListener
{
    private StartScreenRenderer startScreenRenderer;

    public StartScreenKeyListener(StartScreenRenderer startScreenRenderer)
    {
        this.startScreenRenderer = startScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        return DispatchKeyListener.STATE_GAME_MOTION;
    }
}
