package rznw.ui;

import rznw.map.GameWorld;
import rznw.save.MissingFileException;
import rznw.save.LoadException;
import rznw.save.NewGamePlusLoader;

import java.awt.event.KeyEvent;

public class NewGamePlusScreenKeyListener extends StateTransitionKeyListener
{
    private NewGamePlusScreenRenderer newGamePlusScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private boolean loaded;

    public NewGamePlusScreenKeyListener(NewGamePlusScreenRenderer newGamePlusScreenRenderer, GameWorld gameWorld)
    {
        this.newGamePlusScreenRenderer = newGamePlusScreenRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(10);
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
                NewGamePlusLoader gameLoader = new NewGamePlusLoader();
                try
                {
                    gameLoader.load(this.gameWorld, this.state.getEntryNumber());
                    this.loaded = true;
                }
                catch (LoadException le)
                {
                }

                break;
        }

        this.newGamePlusScreenRenderer.render(this.state);
    }

    public void enterState(int previousState)
    {
        this.loaded = false;

        this.newGamePlusScreenRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_NEW_GAME_TYPE_SCREEN;
        }

        if (this.loaded)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_NEW_GAME_PLUS_SCREEN;
    }

}
