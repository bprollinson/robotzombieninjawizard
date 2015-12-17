package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class EquipmentScreenKeyListener extends StateTransitionKeyListener
{
    private EquipmentScreenRenderer equipmentScreenRenderer;
    private GameWorld gameWorld;

    public EquipmentScreenKeyListener(EquipmentScreenRenderer equipmentScreenRenderer, GameWorld gameWorld)
    {
        this.equipmentScreenRenderer = equipmentScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.equipmentScreenRenderer.render();
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

        return DispatchKeyListener.STATE_EQUIPMENT_SCREEN;
    }
}
