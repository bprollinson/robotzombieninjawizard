package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;

import java.awt.event.KeyEvent;

public class InventoryScreenKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private InventoryScreenRenderer inventoryScreenRenderer;
    private GameWorld gameWorld;
    private MainCharacterTurnHandler turnHandler;
    private MenuState state;
    private boolean itemUsed;
    private boolean showingDescription = false;

    public InventoryScreenKeyListener(InventoryScreenRenderer inventoryScreenRenderer, GameWorld gameWorld, MainCharacterTurnHandler turnHandler)
    {
        this.inventoryScreenRenderer = inventoryScreenRenderer;
        this.gameWorld = gameWorld;
        this.turnHandler = turnHandler;
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                if (this.state != null)
                {
                    MainCharacter mainCharacter = this.gameWorld.getMainCharacter();
                    mainCharacter.useItem(this.state.getEntryNumber(), this.gameWorld);
                    this.turnHandler.handlePostTurn();
                    this.itemUsed = true;
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
            case InventoryScreenKeyListener.KEY_I:
                if (this.state != null)
                {
                    this.showingDescription = !this.showingDescription;
                }
                break;
        }

        this.inventoryScreenRenderer.render(this.gameWorld.getMainCharacter(), this.state, this.showingDescription);
    }

    public void enterState(int previousState)
    {
        this.itemUsed = false;

        this.fixPosition();
        this.inventoryScreenRenderer.render(this.gameWorld.getMainCharacter(), this.state, this.showingDescription);
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

        if (this.gameWorld.getMainCharacter().isDead())
        {
            return DispatchKeyListener.STATE_DEATH_SCREEN;
        }

        if (this.gameWorld.getMainCharacter().getPendingLevels() > 0)
        {
            return DispatchKeyListener.STATE_LEVEL_UP_STATS_MENU;
        }

        if (this.gameWorld.gameCompleted())
        {
            return DispatchKeyListener.STATE_GAME_COMPLETED;
        }

        if (this.itemUsed)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_INVENTORY_SCREEN;
    }

    private void fixPosition()
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
    }
}
