package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class SellItemsMenuKeyListener extends StateTransitionKeyListener
{
    private ShopScreenRenderer shopScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;

    public SellItemsMenuKeyListener(ShopScreenRenderer shopScreenRenderer, GameWorld gameWorld)
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

        return DispatchKeyListener.STATE_SELL_ITEMS_MENU;
    }

    public void enterState(int previousState)
    {
        MainCharacter character = gameWorld.getMainCharacter();
        Inventory inventory = character.getInventory();
        this.state = new MenuState(inventory.getNumItemGroups());

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
                this.processSell();
                break;
        }
    }

    public void exitState(KeyEvent event)
    {
    }

    private void renderMenu()
    {
        String menuTitle = "Sell Items";
        String priceDisplay = "";
        Inventory inventory = this.gameWorld.getMainCharacter().getInventory();
        if (this.state.hasEntries())
        {
            int price = inventory.getItemGroup(this.state.getEntryNumber()).getItem().getSellPrice();
            priceDisplay = "Sell Price: " + price;
        }
        this.shopScreenRenderer.renderInventorySubMenu(this.gameWorld.getMainCharacter(), menuTitle, priceDisplay, inventory, this.state);
    }

    private void processSell()
    {
        if (!this.state.hasEntries())
        {
            return;
        }

        MainCharacter character = gameWorld.getMainCharacter();

        Inventory inventory = character.getInventory();
        InventoryItemGroup existingGroup = inventory.getItemGroup(this.state.getEntryNumber());
        InventoryItemGroup removalGroup = new InventoryItemGroup(existingGroup.getItem(), 1);
        inventory.removeItems(removalGroup);

        int goldGained = existingGroup.getItem().getSellPrice();
        inventory.addGold(goldGained);

        this.state.adjustNumEntries(inventory.getNumItemGroups());

        this.renderMenu();
    }
}
