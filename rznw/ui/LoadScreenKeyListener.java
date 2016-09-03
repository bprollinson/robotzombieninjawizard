package rznw.ui;

import java.awt.event.KeyEvent;

import rznw.map.GameWorld;
import rznw.save.GameLoader;
import rznw.save.MissingFileException;

public class LoadScreenKeyListener extends StateTransitionKeyListener
{
    private LoadScreenRenderer loadScreenRenderer;
    private MenuState state;
    private GameWorld gameWorld;
    private int previousState;
    private boolean loaded;

    public LoadScreenKeyListener(LoadScreenRenderer loadScreenRenderer, GameWorld gameWorld)
    {
        this.loadScreenRenderer = loadScreenRenderer;
        this.state = new MenuState(9);
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
                GameLoader gameLoader = new GameLoader();
                try
                {
                    gameLoader.load(this.gameWorld, this.state.getEntryNumber());
                    this.loaded = true;
                }
                catch (MissingFileException mfe)
                {
                }

                break;
        }

        this.loadScreenRenderer.render(this.state);
    }

    public void enterState(int previousState)
    {
        this.previousState = previousState;
        this.loaded = false;

        this.loadScreenRenderer.render(this.state);
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

        if (this.loaded)
        {
            return DispatchKeyListener.STATE_LOAD_CONFIRMATION_SCREEN;
        }

        return DispatchKeyListener.STATE_LOAD_SCREEN;
    }
}
