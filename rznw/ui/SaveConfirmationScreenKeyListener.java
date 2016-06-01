package rznw.ui;

import java.awt.event.KeyEvent;

public class SaveConfirmationScreenKeyListener extends StateTransitionKeyListener
{
    private SaveConfirmationScreenRenderer saveConfirmationScreenRenderer;

    public SaveConfirmationScreenKeyListener(SaveConfirmationScreenRenderer saveConfirmationScreenRenderer)
    {
        this.saveConfirmationScreenRenderer = saveConfirmationScreenRenderer;
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
        return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
    }
}
