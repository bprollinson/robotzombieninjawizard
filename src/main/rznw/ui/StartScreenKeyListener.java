package rznw.ui;

import java.awt.event.KeyEvent;

public class StartScreenKeyListener extends StateTransitionKeyListener
{
    private static final int ENTRY_LOAD = 0;
    private static final int ENTRY_NEW_GAME = 1;
    private static final int ENTRY_EXIT = 2;

    private StartScreenRenderer startScreenRenderer;
    private MenuState state;

    public StartScreenKeyListener(StartScreenRenderer startScreenRenderer)
    {
        this.startScreenRenderer = startScreenRenderer;
        this.state = new MenuState(3);
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

    public void enterState(int previousState)
    {
        this.startScreenRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == StartScreenKeyListener.ENTRY_LOAD)
        {
            return DispatchKeyListener.STATE_LOAD_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == StartScreenKeyListener.ENTRY_NEW_GAME)
        {
            return DispatchKeyListener.STATE_NEW_GAME_TYPE_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == StartScreenKeyListener.ENTRY_EXIT)
        {
            return DispatchKeyListener.STATE_EXIT_SCREEN;
        }

        return DispatchKeyListener.STATE_START_SCREEN;
    }
}
