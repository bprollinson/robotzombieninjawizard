package rznw.ui;

import java.awt.event.KeyEvent;

public class LoadScreenKeyListener extends StateTransitionKeyListener
{
    private LoadScreenRenderer loadScreenRenderer;
    private MenuState state;

    public LoadScreenKeyListener(LoadScreenRenderer loadScreenRenderer)
    {
        this.loadScreenRenderer = loadScreenRenderer;
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

        this.loadScreenRenderer.render(this.state);
    }

    public void enterState(int previousState)
    {
        this.loadScreenRenderer.render(this.state);
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

        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            return DispatchKeyListener.STATE_LOAD_CONFIRMATION_SCREEN;
        }

        return DispatchKeyListener.STATE_LOAD_SCREEN;
    }
}
