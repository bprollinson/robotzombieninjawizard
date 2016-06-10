package rznw.ui;

import java.awt.event.KeyEvent;

public class LoadConfirmationScreenKeyListener extends StateTransitionKeyListener
{
    private LoadConfirmationScreenRenderer loadConfirmationScreenRenderer;

    public LoadConfirmationScreenKeyListener(LoadConfirmationScreenRenderer loadConfirmationScreenRenderer)
    {
        this.loadConfirmationScreenRenderer = loadConfirmationScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.loadConfirmationScreenRenderer.render();
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
    }
}
