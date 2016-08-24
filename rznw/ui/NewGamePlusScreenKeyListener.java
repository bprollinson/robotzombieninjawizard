package rznw.ui;

import java.awt.event.KeyEvent;

public class NewGamePlusScreenKeyListener extends StateTransitionKeyListener
{
    private NewGamePlusScreenRenderer newGamePlusScreenRenderer;

    public NewGamePlusScreenKeyListener(NewGamePlusScreenRenderer newGamePlusScreenRenderer)
    {
        this.newGamePlusScreenRenderer = newGamePlusScreenRenderer;
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
        return DispatchKeyListener.STATE_NEW_GAME_PLUS_SCREEN;
    }

}
