package rznw.ui;

import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;

import java.awt.event.KeyEvent;

public class ShopScreenKeyListener extends StateTransitionKeyListener
{
    private ShopScreenRenderer shopScreenRenderer;
    private GameWorld gameWorld;
    private MainCharacterTurnHandler turnHandler;
    private MenuState topMenuState;
    private MenuState subMenuState;
    private boolean inSubmenu;
    private boolean done;

    public ShopScreenKeyListener(ShopScreenRenderer shopScreenRenderer, GameWorld gameWorld, MainCharacterTurnHandler turnHandler)
    {
        this.shopScreenRenderer = shopScreenRenderer;
        this.gameWorld = gameWorld;
        this.turnHandler = turnHandler;
        this.topMenuState = new MenuState(4);
        this.subMenuState = new MenuState(2);
    }

    public void enterState(int previousState)
    {
        this.inSubmenu = false;
        this.done = false;

        this.shopScreenRenderer.renderTopMenu(gameWorld.getMainCharacter(), topMenuState);
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                if (!this.inSubmenu)
                {
                    this.topMenuState.moveUp();
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                if (!this.inSubmenu)
                {
                    this.topMenuState.moveDown();
                }
                break;
            case KeyEvent.VK_ENTER:
                if (!this.inSubmenu && this.topMenuState.getEntryNumber() <= 3)
                {
                    this.inSubmenu = true;
                }
                else if (!this.inSubmenu)
                {
                    this.done = true;
                }
                else if (this.topMenuState.getEntryNumber() == 2)
                {
                    MainCharacter character = gameWorld.getMainCharacter();

                    Inventory inventory = character.getInventory();
                    InventoryItemGroup existingGroup = inventory.getItemGroup(this.subMenuState.getEntryNumber());
                    InventoryItemGroup removalGroup = new InventoryItemGroup(existingGroup.getItem(), 1);
                    inventory.removeItems(removalGroup);

                    int goldGained = (int)Math.floor(0.6 * existingGroup.getItem().getValue());
                    inventory.addGold(goldGained);
                }

                break;
            case KeyEvent.VK_ESCAPE:
                if (this.inSubmenu)
                {
                    this.inSubmenu = false;
                }
                else
                {
                    this.done = true;
                }

                break;
        }

        if (!this.inSubmenu)
        {
            this.shopScreenRenderer.renderTopMenu(gameWorld.getMainCharacter(), topMenuState);
        }
        else
        {
            String menuTitle = null;
            Inventory inventory = new Inventory();

            switch (this.topMenuState.getEntryNumber())
            {
                case 0:
                    menuTitle = "Buy Items";
                    break;
                case 1:
                    menuTitle = "Buy Equipment";
                    break;
                case 2:
                    menuTitle = "Sell Items";
                    inventory = gameWorld.getMainCharacter().getInventory();
                    break;
                case 3:
                    menuTitle = "Sell Equipment";
                    break;
            }

            this.shopScreenRenderer.renderSubMenu(gameWorld.getMainCharacter(), menuTitle, inventory, this.subMenuState);
        }
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {   
        if (this.done)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_SHOP;
    }
}
