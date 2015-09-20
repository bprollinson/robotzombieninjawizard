package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DispatchKeyListener implements KeyListener
{
    public static final int STATE_GAME_MOTION = 1;
    public static final int STATE_GAME_ESCAPE_MENU = 2;

    private int state;
    private MovementKeyListener movementKeyListener;
    private MainMenuKeyListener mainMenuKeyListener;

    public DispatchKeyListener(MovementKeyListener movementKeyListener, MainMenuKeyListener mainMenuKeyListener)
    {
        this.state = DispatchKeyListener.STATE_GAME_MOTION;
        this.movementKeyListener = movementKeyListener;
        this.mainMenuKeyListener = mainMenuKeyListener;
    }

    public void keyPressed(KeyEvent event)
    {
        if (this.state == DispatchKeyListener.STATE_GAME_MOTION)
        {
            this.movementKeyListener.keyPressed(event);
            this.state = this.movementKeyListener.getNextState(event);

            if (this.state == DispatchKeyListener.STATE_GAME_ESCAPE_MENU)
            {
                this.mainMenuKeyListener.enterState();
            }
        }
        else if (this.state == DispatchKeyListener.STATE_GAME_ESCAPE_MENU)
        {
            this.mainMenuKeyListener.keyPressed(event);
            this.state = this.mainMenuKeyListener.getNextState(event);

            if (this.state == DispatchKeyListener.STATE_GAME_MOTION)
            {
                this.movementKeyListener.enterState();
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
