package rznw.ui;

import rznw.map.GameWorld;
import rznw.save.NewGamePlusLoader;

import java.awt.event.KeyEvent;

public class NewGamePlusScreenKeyListener extends StateTransitionKeyListener
{
    private NewGamePlusScreenRenderer newGamePlusScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;

    public NewGamePlusScreenKeyListener(NewGamePlusScreenRenderer newGamePlusScreenRenderer, GameWorld gameWorld)
    {
        this.newGamePlusScreenRenderer = newGamePlusScreenRenderer;
        this.gameWorld = gameWorld;
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
            case KeyEvent.VK_ENTER:
                NewGamePlusLoader gameLoader = new NewGamePlusLoader();
                gameLoader.load(this.gameWorld, this.state.getEntryNumber());
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
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_NEW_GAME_TYPE_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_NEW_GAME_PLUS_SCREEN;
    }

}
