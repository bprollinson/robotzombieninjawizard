package rznw.ui;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

public class DispatchKeyListener implements KeyListener
{
    private KeyListener movementKeyListener;

    public DispatchKeyListener(KeyListener movementKeyListener)
    {
        this.movementKeyListener = movementKeyListener;
    }

    public void keyPressed(KeyEvent event)
    {
        this.movementKeyListener.keyPressed(event);
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }
}
