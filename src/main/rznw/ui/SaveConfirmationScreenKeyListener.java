package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class SaveConfirmationScreenKeyListener extends StateTransitionKeyListener
{
    private SaveConfirmationScreenRenderer saveConfirmationScreenRenderer;
    private GameWorld gameWorld;

    public SaveConfirmationScreenKeyListener(SaveConfirmationScreenRenderer saveConfirmationScreenRenderer, GameWorld gameWorld)
    {
        this.saveConfirmationScreenRenderer = saveConfirmationScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.saveConfirmationScreenRenderer.render();
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (this.gameWorld.gameCompleted())
        {
            return DispatchKeyListener.STATE_START_SCREEN;
        }

        return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
    }
}
