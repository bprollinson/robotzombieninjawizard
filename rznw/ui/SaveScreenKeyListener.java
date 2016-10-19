package rznw.ui;

import java.awt.event.KeyEvent;

import rznw.map.GameWorld;
import rznw.save.GameSaver;

public class SaveScreenKeyListener extends StateTransitionKeyListener
{
    private SaveScreenRenderer saveScreenRenderer;
    private MenuState state;
    private GameWorld gameWorld;
    private int previousState;

    public SaveScreenKeyListener(SaveScreenRenderer saveScreenRenderer, GameWorld gameWorld)
    {
        this.saveScreenRenderer = saveScreenRenderer;
        this.state = new MenuState(10);
        this.gameWorld = gameWorld;
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
            case KeyEvent.VK_ENTER:
                GameSaver gameSaver = new GameSaver();
                gameSaver.save(this.gameWorld, this.state.getEntryNumber());
                break;
        }

        this.saveScreenRenderer.render(this.state);
    }

    public void enterState(int previousState)
    {
        this.previousState = previousState;

        this.saveScreenRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE && this.previousState == DispatchKeyListener.STATE_GAME_COMPLETED)
        {
            return DispatchKeyListener.STATE_START_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            return DispatchKeyListener.STATE_SAVE_CONFIRMATION_SCREEN;
        }

        return DispatchKeyListener.STATE_SAVE_SCREEN;
    }
}
