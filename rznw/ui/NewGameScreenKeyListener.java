package rznw.ui;

import java.awt.event.KeyEvent;

public class NewGameScreenKeyListener extends StateTransitionKeyListener
{
    private NewGameScreenRenderer newGameScreenRenderer;
    private MenuState state;

    public NewGameScreenKeyListener(NewGameScreenRenderer newGameScreenRenderer)
    {
        this.newGameScreenRenderer = newGameScreenRenderer;
        this.state = new MenuState(1);
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

        this.newGameScreenRenderer.render(this.state);
    }

    public void enterState()
    {
        this.newGameScreenRenderer.render(this.state);
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
