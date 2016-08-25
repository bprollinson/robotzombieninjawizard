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
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.state.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.state.moveDown();
                break;
        }

        this.newGamePlusScreenRenderer.render(this.state);
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
