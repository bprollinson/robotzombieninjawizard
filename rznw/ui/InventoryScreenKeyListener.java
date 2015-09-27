package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class InventoryScreenKeyListener extends StateTransitionKeyListener
{
    private InventoryScreenRenderer inventoryScreenRenderer;
    private MainCharacter character;

    public InventoryScreenKeyListener(InventoryScreenRenderer inventoryScreenRenderer, MainCharacter character)
    {
        this.inventoryScreenRenderer = inventoryScreenRenderer;
        this.character = character;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.inventoryScreenRenderer.render(character);
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
