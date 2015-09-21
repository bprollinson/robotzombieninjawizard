package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InventoryScreenKeyListener implements KeyListener
{
    private InventoryScreenRenderer inventoryScreenRenderer;

    public InventoryScreenKeyListener(InventoryScreenRenderer inventoryScreenRenderer)
    {
        this.inventoryScreenRenderer = inventoryScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.inventoryScreenRenderer.render();
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
