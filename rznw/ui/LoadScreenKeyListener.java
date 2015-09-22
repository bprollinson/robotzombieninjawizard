package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoadScreenKeyListener implements KeyListener
{
    private LoadScreenRenderer loadScreenRenderer;

    public LoadScreenKeyListener(LoadScreenRenderer loadScreenRenderer)
    {
        this.loadScreenRenderer = loadScreenRenderer;
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
        this.loadScreenRenderer.render();
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_LOAD_SCREEN;
    }
}
