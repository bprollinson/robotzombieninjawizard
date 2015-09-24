package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DispatchKeyListener implements KeyListener
{
    public static final int STATE_GAME_MOTION = 1;
    public static final int STATE_GAME_ESCAPE_MENU = 2;
    public static final int STATE_CHARACTER_SCREEN = 3;
    public static final int STATE_SKILLS_SCREEN = 4;
    public static final int STATE_SPELLS_SCREEN = 5;
    public static final int STATE_INVENTORY_SCREEN = 6;
    public static final int STATE_SAVE_SCREEN = 7;
    public static final int STATE_LOAD_SCREEN = 8;
    public static final int STATE_NEW_GAME_SCREEN = 9;
    public static final int STATE_GAME_EXIT = 10;

    private int state;
    private StateTransitionKeyListenerContainer listenerContainer;

    public DispatchKeyListener(StateTransitionKeyListenerContainer listenerContainer)
    {
        this.state = DispatchKeyListener.STATE_GAME_MOTION;
        this.listenerContainer = listenerContainer;
    }

    public void keyPressed(KeyEvent event)
    {
        int lastState = this.state;
        StateTransitionKeyListener lastStateListener = this.listenerContainer.getListener(this.state);

        lastStateListener.keyPressed(event);
        this.state = lastStateListener.getNextState(event);

        if (this.state == DispatchKeyListener.STATE_GAME_EXIT)
        {
            System.exit(0);
        }

        if (this.state != lastState)
        {
            StateTransitionKeyListener currentStateListener = this.listenerContainer.getListener(this.state);
            currentStateListener.enterState();
        }
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }
}
