package rznw.ui;

import java.awt.event.KeyEvent;

public class InventoryScreenKeyListener extends StateTransitionKeyListener
{
    private InventoryScreenRenderer inventoryScreenRenderer;

    public InventoryScreenKeyListener(InventoryScreenRenderer inventoryScreenRenderer)
    {
        this.inventoryScreenRenderer = inventoryScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.inventoryScreenRenderer.render();
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
