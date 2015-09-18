package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DispatchKeyListener implements KeyListener
{
    public static final int STATE_GAME_MOTION = 1;
    public static final int STATE_GAME_ESCAPE_MENU = 2;

    private int state;
    private MovementKeyListener movementKeyListener;

    public DispatchKeyListener(MovementKeyListener movementKeyListener)
    {
        this.state = DispatchKeyListener.STATE_GAME_MOTION;
        this.movementKeyListener = movementKeyListener;
    }

    public void keyPressed(KeyEvent event)
    {
        if (this.state == DispatchKeyListener.STATE_GAME_MOTION)
        {
            this.movementKeyListener.keyPressed(event);
            this.state = this.movementKeyListener.getNextState(event);

            if (this.state == DispatchKeyListener.STATE_GAME_ESCAPE_MENU)
            {
                System.out.println("Display the escape menu");
            }
        }
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }
}
