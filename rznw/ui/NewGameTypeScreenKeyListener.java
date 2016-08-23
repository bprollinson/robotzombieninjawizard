package rznw.ui;

import java.awt.event.KeyEvent;

public class NewGameTypeScreenKeyListener extends StateTransitionKeyListener
{
    private NewGameTypeScreenRenderer newGameTypeScreenRenderer;

    public NewGameTypeScreenKeyListener(NewGameTypeScreenRenderer newGameTypeScreenRenderer)
    {
        this.newGameTypeScreenRenderer = newGameTypeScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        return DispatchKeyListener.STATE_NEW_GAME_TYPE_SCREEN;
    }
}
