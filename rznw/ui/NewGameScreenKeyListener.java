package rznw.ui;

import java.awt.event.KeyEvent;

public class NewGameScreenKeyListener extends StateTransitionKeyListener
{
    private NewGameScreenRenderer newGameScreenRenderer;

    public NewGameScreenKeyListener(NewGameScreenRenderer newGameScreenRenderer)
    {
        this.newGameScreenRenderer = newGameScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.newGameScreenRenderer.render();
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_NEW_GAME_SCREEN;
    }
}
