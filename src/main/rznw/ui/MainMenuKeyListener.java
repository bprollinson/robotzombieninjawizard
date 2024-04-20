package rznw.ui;

import java.awt.event.KeyEvent;

public class MainMenuKeyListener extends StateTransitionKeyListener
{
    private static final int ENTRY_RESUME = 0;
    private static final int ENTRY_CHARACTER = 1;
    private static final int ENTRY_SKILLS = 2;
    private static final int ENTRY_SPELLS = 3;
    private static final int ENTRY_INVENTORY = 4;
    private static final int ENTRY_EQUIPMENT = 5;
    private static final int ENTRY_LOGS = 6;
    private static final int ENTRY_INSTRUCTIONS = 7;
    private static final int ENTRY_SAVE = 8;
    private static final int ENTRY_LOAD = 9;
    private static final int ENTRY_NEW_GAME = 10;
    private static final int ENTRY_EXIT = 11;

    private MainMenuRenderer mainMenuRenderer;
    private MenuState state;

    public MainMenuKeyListener(MainMenuRenderer mainMenuRenderer)
    {
        this.mainMenuRenderer = mainMenuRenderer;
        this.state = new MenuState(12);
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

    public void enterState(int previousState)
    {
        this.mainMenuRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            switch (this.state.getEntryNumber())
            {
                case MainMenuKeyListener.ENTRY_RESUME:
                    return DispatchKeyListener.STATE_GAME_MOTION;
                case MainMenuKeyListener.ENTRY_CHARACTER:
                    return DispatchKeyListener.STATE_CHARACTER_SCREEN;
                case MainMenuKeyListener.ENTRY_SKILLS:
                    return DispatchKeyListener.STATE_SKILLS_SCREEN;
                case MainMenuKeyListener.ENTRY_SPELLS:
                    return DispatchKeyListener.STATE_SPELLS_SCREEN;
                case MainMenuKeyListener.ENTRY_INVENTORY:
                    return DispatchKeyListener.STATE_INVENTORY_SCREEN;
                case MainMenuKeyListener.ENTRY_EQUIPMENT:
                    return DispatchKeyListener.STATE_EQUIPMENT_SCREEN;
                case MainMenuKeyListener.ENTRY_LOGS:
                    return DispatchKeyListener.STATE_LOGS_SCREEN;
                case MainMenuKeyListener.ENTRY_INSTRUCTIONS:
                    return DispatchKeyListener.STATE_INSTRUCTIONS_SCREEN;
                case MainMenuKeyListener.ENTRY_SAVE:
                    return DispatchKeyListener.STATE_SAVE_SCREEN;
                case MainMenuKeyListener.ENTRY_LOAD:
                    return DispatchKeyListener.STATE_LOAD_SCREEN;
                case MainMenuKeyListener.ENTRY_NEW_GAME:
                    return DispatchKeyListener.STATE_NEW_GAME_TYPE_SCREEN;
                case MainMenuKeyListener.ENTRY_EXIT:
                    return DispatchKeyListener.STATE_EXIT_SCREEN;
            }
        }

        return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
    }
}
