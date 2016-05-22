package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DispatchKeyListener implements KeyListener
{
    public static final int STATE_START_SCREEN = 1;
    public static final int STATE_GAME_MOTION = 2;
    public static final int STATE_GAME_ESCAPE_MENU = 3;
    public static final int STATE_CHARACTER_SCREEN = 4;
    public static final int STATE_SKILLS_SCREEN = 5;
    public static final int STATE_SPELLS_SCREEN = 6;
    public static final int STATE_INVENTORY_SCREEN = 7;
    public static final int STATE_SAVE_SCREEN = 8;
    public static final int STATE_LOAD_SCREEN = 9;
    public static final int STATE_NEW_GAME_SCREEN = 10;
    public static final int STATE_EXIT_SCREEN = 11;
    public static final int STATE_GAME_EXIT = 12;
    public static final int STATE_LEVEL_UP_STATS_MENU = 13;
    public static final int STATE_LEVEL_UP_SKILLS_MENU = 14;
    public static final int STATE_LEVEL_UP_SPELLS_MENU = 15;
    public static final int STATE_DEATH_SCREEN = 16;
    public static final int STATE_EQUIPMENT_SCREEN = 17;
    public static final int STATE_WEAPONS_SCREEN = 18;
    public static final int STATE_SHIELDS_SCREEN = 19;
    public static final int STATE_ARMOR_SCREEN = 20;
    public static final int STATE_DETECT_VITALITY = 21;
    public static final int STATE_TRADE_ITEMS = 22;
    public static final int STATE_SHOP = 23;
    public static final int STATE_INSTRUCTIONS_SCREEN = 24;
    public static final int STATE_GAME_COMPLETED = 25;

    private int state;
    private StateTransitionKeyListenerContainer listenerContainer;

    public DispatchKeyListener(StateTransitionKeyListenerContainer listenerContainer)
    {
        this.state = DispatchKeyListener.STATE_START_SCREEN;
        this.listenerContainer = listenerContainer;
    }

    public void enterFirstState()
    {
        this.listenerContainer.getListener(this.state).enterState(0);
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
            lastStateListener.exitState(event);
            StateTransitionKeyListener currentStateListener = this.listenerContainer.getListener(this.state);
            currentStateListener.enterState(lastState);
        }
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }
}
