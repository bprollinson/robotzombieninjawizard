package rznw.ui;

import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class BuyItemsMenuKeyListener extends StateTransitionKeyListener
{
    private ShopScreenRenderer shopScreenRenderer;
    private GameWorld gameWorld;
    private Inventory buyInventory;
    private MenuState state;

    public BuyItemsMenuKeyListener(ShopScreenRenderer shopScreenRenderer, GameWorld gameWorld)
    {
        this.shopScreenRenderer = shopScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_SHOP;
        }

        return DispatchKeyListener.STATE_BUY_ITEMS_MENU;
    }

    public void enterState(int previousState)
    {
        this.buyInventory = this.gameWorld.getShopInventory().getRandomItems();
        this.state = new MenuState(this.buyInventory.getNumItemGroups());

        this.renderMenu();
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.state.moveUp();
                this.renderMenu();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.state.moveDown();
                this.renderMenu();
                break;
            case KeyEvent.VK_ENTER:
                this.processBuy();
                break;
        }
    }

    public void exitState(KeyEvent event)
    {
    }

    private void renderMenu()
    {
        String menuTitle = "Buy Items";
        String priceDisplay = "";
        if (this.state.hasEntries())
        {
            int price = this.buyInventory.getItemGroup(this.state.getEntryNumber()).getItem().getBuyPrice(this.gameWorld.getMainCharacter());
            priceDisplay = "Purchase Price: " + price;
        }
        this.shopScreenRenderer.renderInventorySubMenu(this.gameWorld.getMainCharacter(), menuTitle, priceDisplay, this.buyInventory, this.state);
    }

    private void processBuy()
    {
        if (!this.state.hasEntries())
        {
            return;
        }

        MainCharacter character = this.gameWorld.getMainCharacter();
        InventoryItemGroup selectedGroup = this.buyInventory.getItemGroup(this.state.getEntryNumber());

        int itemCost = selectedGroup.getItem().getBuyPrice(character);

        if (character.getInventory().getNumGold() >= itemCost)
        {
            try
            {
                character.getInventory().addItems(new InventoryItemGroup(selectedGroup.getItem(), 1));
                this.buyInventory.removeItems(new InventoryItemGroup(selectedGroup.getItem(), 1));

                character.getInventory().removeGold(itemCost);

                this.state.adjustNumEntries(this.buyInventory.getNumItemGroups());
            }
            catch (InventoryFullException ife)
            {
            }

            this.renderMenu();
        }
    }
}
