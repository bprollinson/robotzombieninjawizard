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
    private MovementKeyListener movementKeyListener;
    private MainMenuKeyListener mainMenuKeyListener;
    private CharacterScreenKeyListener characterScreenKeyListener;
    private SkillsScreenKeyListener skillsScreenKeyListener;
    private SpellsScreenKeyListener spellsScreenKeyListener;
    private InventoryScreenKeyListener inventoryScreenKeyListener;
    private SaveScreenKeyListener saveScreenKeyListener;
    private LoadScreenKeyListener loadScreenKeyListener;
    private NewGameScreenKeyListener newGameScreenKeyListener;

    public DispatchKeyListener(MovementKeyListener movementKeyListener, MainMenuKeyListener mainMenuKeyListener, CharacterScreenKeyListener characterScreenKeyListener, SkillsScreenKeyListener skillsScreenKeyListener, SpellsScreenKeyListener spellsScreenKeyListener, InventoryScreenKeyListener inventoryScreenKeyListener, SaveScreenKeyListener saveScreenKeyListener, LoadScreenKeyListener loadScreenKeyListener, NewGameScreenKeyListener newGameScreenKeyListener)
    {
        this.state = DispatchKeyListener.STATE_GAME_MOTION;
        this.movementKeyListener = movementKeyListener;
        this.mainMenuKeyListener = mainMenuKeyListener;
        this.characterScreenKeyListener = characterScreenKeyListener;
        this.skillsScreenKeyListener = skillsScreenKeyListener;
        this.spellsScreenKeyListener = spellsScreenKeyListener;
        this.inventoryScreenKeyListener = inventoryScreenKeyListener;
        this.saveScreenKeyListener = saveScreenKeyListener;
        this.loadScreenKeyListener = loadScreenKeyListener;
        this.newGameScreenKeyListener = newGameScreenKeyListener;
    }

    public void keyPressed(KeyEvent event)
    {
        int lastState = this.state;
        StateTransitionKeyListener lastStateListener = this.getListener(this.state);

        lastStateListener.keyPressed(event);
        this.state = lastStateListener.getNextState(event);

        if (this.state == DispatchKeyListener.STATE_GAME_EXIT)
        {
            System.exit(0);
        }

        if (this.state != lastState)
        {
            StateTransitionKeyListener currentStateListener = this.getListener(this.state);
            currentStateListener.enterState();
        }
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }

    private StateTransitionKeyListener getListener(int state)
    {
        switch (state)
        {
            case DispatchKeyListener.STATE_GAME_MOTION:
                return this.movementKeyListener;
            case DispatchKeyListener.STATE_GAME_ESCAPE_MENU:
                return this.mainMenuKeyListener;
            case DispatchKeyListener.STATE_CHARACTER_SCREEN:
                return this.characterScreenKeyListener;
            case DispatchKeyListener.STATE_SKILLS_SCREEN:
                return this.skillsScreenKeyListener;
            case DispatchKeyListener.STATE_SPELLS_SCREEN:
                return this.spellsScreenKeyListener;
            case DispatchKeyListener.STATE_INVENTORY_SCREEN:
                return this.inventoryScreenKeyListener;
            case DispatchKeyListener.STATE_SAVE_SCREEN:
                return this.saveScreenKeyListener;
            case DispatchKeyListener.STATE_LOAD_SCREEN:
                return this.loadScreenKeyListener;
            case DispatchKeyListener.STATE_NEW_GAME_SCREEN:
                return this.newGameScreenKeyListener;
            default:
                return null;
        }
    }
}
