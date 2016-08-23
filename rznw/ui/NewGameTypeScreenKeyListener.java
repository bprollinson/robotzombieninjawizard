package rznw.ui;

import java.awt.event.KeyEvent;

public class NewGameTypeScreenKeyListener extends StateTransitionKeyListener
{
    private NewGameTypeScreenRenderer newGameTypeScreenRenderer;
    private MenuState state;
    private int previousState;

    public NewGameTypeScreenKeyListener(NewGameTypeScreenRenderer newGameTypeScreenRenderer)
    {
        this.newGameTypeScreenRenderer = newGameTypeScreenRenderer;
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

        this.newGameTypeScreenRenderer.render(this.state);
    }

    public void enterState(int previousState)
    {
        if (previousState != DispatchKeyListener.STATE_NEW_GAME_SCREEN)
        {
            this.previousState = previousState;
        }

        this.newGameTypeScreenRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return this.previousState;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            switch (this.state.getEntryNumber())
            {
                case 0:
                    return DispatchKeyListener.STATE_NEW_GAME_SCREEN;
            }
        }

        return DispatchKeyListener.STATE_NEW_GAME_TYPE_SCREEN;
    }
}
