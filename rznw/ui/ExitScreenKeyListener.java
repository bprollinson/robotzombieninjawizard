package rznw.ui;

import java.awt.event.KeyEvent;

public class ExitScreenKeyListener extends StateTransitionKeyListener
{
    private static final int ENTRY_YES = 0;
    private static final int ENTRY_NO = 1;

    private ExitScreenRenderer exitScreenRenderer;
    private MenuState state;

    public ExitScreenKeyListener(ExitScreenRenderer exitScreenRenderer)
    {
        this.exitScreenRenderer = exitScreenRenderer;
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

        this.exitScreenRenderer.render(this.state);
    }

    public void enterState()
    {
        this.exitScreenRenderer.render(this.state);
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

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == ExitScreenKeyListener.ENTRY_YES)
        {
            return DispatchKeyListener.STATE_GAME_EXIT;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == ExitScreenKeyListener.ENTRY_NO)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_EXIT_SCREEN;
    }
}
