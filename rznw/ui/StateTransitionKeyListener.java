package rznw.ui;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public abstract class StateTransitionKeyListener implements KeyListener
{
    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }

    public abstract void enterState();

    public abstract void exitState(KeyEvent event);

    public abstract int getNextState(KeyEvent event);
}
