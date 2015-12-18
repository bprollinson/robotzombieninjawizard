package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class EquipmentScreenKeyListener extends StateTransitionKeyListener
{
    private static final int ENTRY_WEAPONS = 0;
    private static final int ENTRY_SHIELDS = 1;

    private EquipmentScreenRenderer equipmentScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;

    public EquipmentScreenKeyListener(EquipmentScreenRenderer equipmentScreenRenderer, GameWorld gameWorld)
    {
        this.equipmentScreenRenderer = equipmentScreenRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(1);
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

        this.equipmentScreenRenderer.render(this.state);
    }

    public void enterState(int previousState)
    {
        this.equipmentScreenRenderer.render(state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == EquipmentScreenKeyListener.ENTRY_WEAPONS)
        {
            return DispatchKeyListener.STATE_WEAPONS_SCREEN;
        }

        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.state.getEntryNumber() == EquipmentScreenKeyListener.ENTRY_SHIELDS)
        {
            return DispatchKeyListener.STATE_SHIELDS_SCREEN;
        }

        return DispatchKeyListener.STATE_EQUIPMENT_SCREEN;
    }
}
