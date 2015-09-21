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
    public static final int STATE_GAME_EXIT = 8;

    private int state;
    private MovementKeyListener movementKeyListener;
    private MainMenuKeyListener mainMenuKeyListener;
    private CharacterScreenKeyListener characterScreenKeyListener;
    private SkillsScreenKeyListener skillsScreenKeyListener;
    private SpellsScreenKeyListener spellsScreenKeyListener;
    private InventoryScreenKeyListener inventoryScreenKeyListener;
    private SaveScreenKeyListener saveScreenKeyListener;

    public DispatchKeyListener(MovementKeyListener movementKeyListener, MainMenuKeyListener mainMenuKeyListener, CharacterScreenKeyListener characterScreenKeyListener, SkillsScreenKeyListener skillsScreenKeyListener, SpellsScreenKeyListener spellsScreenKeyListener, InventoryScreenKeyListener inventoryScreenKeyListener, SaveScreenKeyListener saveScreenKeyListener)
    {
        this.state = DispatchKeyListener.STATE_GAME_MOTION;
        this.movementKeyListener = movementKeyListener;
        this.mainMenuKeyListener = mainMenuKeyListener;
        this.characterScreenKeyListener = characterScreenKeyListener;
        this.skillsScreenKeyListener = skillsScreenKeyListener;
        this.spellsScreenKeyListener = spellsScreenKeyListener;
        this.inventoryScreenKeyListener = inventoryScreenKeyListener;
        this.saveScreenKeyListener = saveScreenKeyListener;
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
            else if (this.state == DispatchKeyListener.STATE_GAME_EXIT)
            {
                System.exit(0);
            }
            else if (this.state == DispatchKeyListener.STATE_CHARACTER_SCREEN)
            {
                this.characterScreenKeyListener.enterState();
            }
            else if (this.state == DispatchKeyListener.STATE_SKILLS_SCREEN)
            {
                this.skillsScreenKeyListener.enterState();
            }
            else if (this.state == DispatchKeyListener.STATE_SPELLS_SCREEN)
            {
                this.spellsScreenKeyListener.enterState();
            }
            else if (this.state == DispatchKeyListener.STATE_INVENTORY_SCREEN)
            {
                this.inventoryScreenKeyListener.enterState();
            }
            else if (this.state == DispatchKeyListener.STATE_SAVE_SCREEN)
            {
                this.saveScreenKeyListener.enterState();
            }
        }
        else if (this.state == DispatchKeyListener.STATE_CHARACTER_SCREEN || this.state == DispatchKeyListener.STATE_SKILLS_SCREEN || this.state == DispatchKeyListener.STATE_SPELLS_SCREEN || this.state == DispatchKeyListener.STATE_INVENTORY_SCREEN || this.state == DispatchKeyListener.STATE_SAVE_SCREEN)
        {
            this.state = this.characterScreenKeyListener.getNextState(event);

            if (this.state == DispatchKeyListener.STATE_GAME_ESCAPE_MENU)
            {
                this.mainMenuKeyListener.enterState();
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
