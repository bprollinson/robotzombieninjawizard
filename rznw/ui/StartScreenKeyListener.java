package rznw.ui;

import java.awt.event.KeyEvent;

public class StartScreenKeyListener extends StateTransitionKeyListener
{
    private StartScreenRenderer startScreenRenderer;
    private MenuState state;

    public StartScreenKeyListener(StartScreenRenderer startScreenRenderer)
    {
        this.startScreenRenderer = startScreenRenderer;
        this.state = new MenuState(2);
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

        this.startScreenRenderer.render(this.state);
    }

    public void enterState()
    {
        this.startScreenRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_START_SCREEN;
    }
}
