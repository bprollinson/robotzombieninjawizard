package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SaveScreenKeyListener implements KeyListener
{
    private SaveScreenRenderer saveScreenRenderer;

    public SaveScreenKeyListener(SaveScreenRenderer saveScreenRenderer)
    {
        this.saveScreenRenderer = saveScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.saveScreenRenderer.render();
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_SAVE_SCREEN;
    }
}
