package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class InventoryScreenKeyListener extends StateTransitionKeyListener
{
    private InventoryScreenRenderer inventoryScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;

    public InventoryScreenKeyListener(InventoryScreenRenderer inventoryScreenRenderer, GameWorld gameWorld)
    {
        this.inventoryScreenRenderer = inventoryScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                if (this.state != null)
                {
                    MainCharacter mainCharacter = this.gameWorld.getMainCharacter();
                    mainCharacter.useItem(this.state.getEntryNumber());
                }
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                if (this.state != null)
                {
                    this.state.moveUp();
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                if (this.state != null)
                {
                    this.state.moveDown();
                }
                break;
        }

        this.inventoryScreenRenderer.render(this.gameWorld.getMainCharacter(), this.state);
    }

    public void enterState(int previousState)
    {
        MainCharacter mainCharacter = this.gameWorld.getMainCharacter();
        if (mainCharacter.getInventory().getNumItemGroups() > 0)
        {
            int entryNumber = this.state == null ? 0 : this.state.getEntryNumber();
            this.state = new MenuState(mainCharacter.getInventory().getNumItemGroups() - 1);
            if (entryNumber >= mainCharacter.getInventory().getNumItemGroups())
            {
                entryNumber = mainCharacter.getInventory().getNumItemGroups() - 1;
            }

            this.state.setEntryNumber(entryNumber);
        }
        else
        {
            this.state = null;
        }

        this.inventoryScreenRenderer.render(this.gameWorld.getMainCharacter(), this.state);
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
