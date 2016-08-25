package rznw.ui;

import java.awt.event.KeyEvent;

public class NewGamePlusScreenKeyListener extends StateTransitionKeyListener
{
    private NewGamePlusScreenRenderer newGamePlusScreenRenderer;
    private MenuState state;

    public NewGamePlusScreenKeyListener(NewGamePlusScreenRenderer newGamePlusScreenRenderer)
    {
        this.newGamePlusScreenRenderer = newGamePlusScreenRenderer;
        this.state = new MenuState(9);
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.newGamePlusScreenRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        return DispatchKeyListener.STATE_NEW_GAME_PLUS_SCREEN;
    }

}
