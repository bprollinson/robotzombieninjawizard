package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class NewGameScreenKeyListener extends StateTransitionKeyListener
{
    private static final int ENTRY_ROBOT = 0;
    private static final int ENTRY_ZOMBIE = 1;
    private static final int ENTRY_NINJA = 2;
    private static final int ENTRY_WIZARD = 3;

    private NewGameScreenRenderer newGameScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;

    public NewGameScreenKeyListener(NewGameScreenRenderer newGameScreenRenderer, GameWorld gameWorld)
    {
        this.newGameScreenRenderer = newGameScreenRenderer;
        this.gameWorld = gameWorld;
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

        this.newGameScreenRenderer.render(this.state);
    }

    public void enterState(int previousState)
    {
        this.newGameScreenRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            System.out.println("Regenerate the world");
            this.gameWorld.initializeToDefaultState(this.state.getEntryNumber());
        }
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_NEW_GAME_SCREEN;
    }
}
