package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DispatchKeyListener implements KeyListener
{
    public static final int STATE_GAME_MOTION = 1;
    public static final int STATE_GAME_ESCAPE_MENU = 2;
    public static final int STATE_CHARACTER_SCREEN = 3;
    public static final int STATE_SKILLS_SCREEN = 4;
    public static final int STATE_GAME_EXIT = 5;

    private int state;
    private MovementKeyListener movementKeyListener;
    private MainMenuKeyListener mainMenuKeyListener;
    private CharacterScreenKeyListener characterScreenKeyListener;
    private SkillsScreenKeyListener skillsScreenKeyListener;

    public DispatchKeyListener(MovementKeyListener movementKeyListener, MainMenuKeyListener mainMenuKeyListener, CharacterScreenKeyListener characterScreenKeyListener, SkillsScreenKeyListener skillsScreenKeyListener)
    {
        this.state = DispatchKeyListener.STATE_GAME_MOTION;
        this.movementKeyListener = movementKeyListener;
        this.mainMenuKeyListener = mainMenuKeyListener;
        this.characterScreenKeyListener = characterScreenKeyListener;
        this.skillsScreenKeyListener = skillsScreenKeyListener;
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
        }
        else if (this.state == DispatchKeyListener.STATE_CHARACTER_SCREEN || this.state == DispatchKeyListener.STATE_SKILLS_SCREEN)
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
