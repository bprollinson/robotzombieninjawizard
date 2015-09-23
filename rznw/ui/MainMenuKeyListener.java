package rznw.ui;

import java.awt.event.KeyEvent;

public class MainMenuKeyListener extends StateTransitionKeyListener
{
    private static final int ENTRY_CHARACTER = 0;
    private static final int ENTRY_SKILLS = 1;
    private static final int ENTRY_SPELLS = 2;
    private static final int ENTRY_INVENTORY = 3;
    private static final int ENTRY_SAVE = 4;
    private static final int ENTRY_LOAD = 5;
    private static final int ENTRY_NEW_GAME = 6;
    private static final int ENTRY_EXIT = 7;

    private MainMenuRenderer mainMenuRenderer;
    private MainMenuState state;

    public MainMenuKeyListener(MainMenuRenderer mainMenuRenderer)
    {
        this.mainMenuRenderer = mainMenuRenderer;
        this.state = new MainMenuState();
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.state.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.state.moveDown();
                break;
        }

        this.mainMenuRenderer.render(this.state);
    }

    public void enterState()
    {
        this.mainMenuRenderer.render(this.state);
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == MainMenuKeyListener.ENTRY_CHARACTER)
        {
            return DispatchKeyListener.STATE_CHARACTER_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == MainMenuKeyListener.ENTRY_SKILLS)
        {
            return DispatchKeyListener.STATE_SKILLS_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == MainMenuKeyListener.ENTRY_SPELLS)
        {
            return DispatchKeyListener.STATE_SPELLS_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == MainMenuKeyListener.ENTRY_INVENTORY)
        {
            return DispatchKeyListener.STATE_INVENTORY_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == MainMenuKeyListener.ENTRY_SAVE)
        {
            return DispatchKeyListener.STATE_SAVE_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == MainMenuKeyListener.ENTRY_LOAD)
        {
            return DispatchKeyListener.STATE_LOAD_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == MainMenuKeyListener.ENTRY_NEW_GAME)
        {
            return DispatchKeyListener.STATE_NEW_GAME_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == MainMenuKeyListener.ENTRY_EXIT)
        {
            return DispatchKeyListener.STATE_GAME_EXIT;
        }

        return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
    }
}
