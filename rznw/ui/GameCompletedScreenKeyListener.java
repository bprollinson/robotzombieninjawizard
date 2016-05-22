package rznw.ui;

import java.awt.event.KeyEvent;

public class GameCompletedScreenKeyListener extends StateTransitionKeyListener
{
    private GameCompletedScreenRenderer renderer;

    public GameCompletedScreenKeyListener(GameCompletedScreenRenderer renderer)
    {
        this.renderer = renderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.renderer.render();
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        return DispatchKeyListener.STATE_START_SCREEN;
    }
}
