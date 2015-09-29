package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class InventoryScreenKeyListener extends StateTransitionKeyListener
{
    private InventoryScreenRenderer inventoryScreenRenderer;
    private GameWorld gameWorld;

    public InventoryScreenKeyListener(InventoryScreenRenderer inventoryScreenRenderer, GameWorld gameWorld)
    {
        this.inventoryScreenRenderer = inventoryScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.inventoryScreenRenderer.render(this.gameWorld.getMainCharacter());
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

        return DispatchKeyListener.STATE_INVENTORY_SCREEN;
    }
}
