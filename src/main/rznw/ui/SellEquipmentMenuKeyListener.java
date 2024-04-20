package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class SellEquipmentMenuKeyListener extends StateTransitionKeyListener
{
    private ShopScreenRenderer shopScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;

    public SellEquipmentMenuKeyListener(ShopScreenRenderer shopScreenRenderer, GameWorld gameWorld)
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

        return DispatchKeyListener.STATE_SELL_EQUIPMENT_MENU;
    }

    public void enterState(int previousState)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        Equipment equipment = character.getEquipment();
        this.state = new MenuState(equipment.getNumGroups());

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
        String menuTitle = "Sell Equipment";
        String priceDisplay = "";
        Equipment equipment = this.gameWorld.getMainCharacter().getEquipment();
        if (this.state.hasEntries())
        {
            int price = equipment.getEquipmentGroup(this.state.getEntryNumber()).getItem().getSellPrice();
            priceDisplay = "Sell Price: " + price;
        }
        this.shopScreenRenderer.renderEquipmentSubMenu(this.gameWorld.getMainCharacter(), menuTitle, priceDisplay, equipment, this.state);
    }

    private void processSell()
    {
        if (!this.state.hasEntries())
        {
            return;
        }

        MainCharacter character = this.gameWorld.getMainCharacter();
        Equipment equipment = character.getEquipment();
        EquipmentGroup equipmentGroup = equipment.getEquipmentGroup(this.state.getEntryNumber());
        EquipmentItem item = equipmentGroup.getItem();

        EquipmentItem equippedItem = character.getEquipment().getEquippedItemOfType(item.getEquipmentType());
        boolean isEquippedItem = equippedItem != null && item.getClass().equals(equippedItem.getClass());

        boolean itemSold = !isEquippedItem || equipmentGroup.getNumItems() > 1;

        if (itemSold)
        {
            Inventory inventory = character.getInventory();
            int goldGained = equipmentGroup.getItem().getSellPrice();
            inventory.addGold(goldGained);
            character.getEquipment().removeEquipment(item);
        }

        this.state.adjustNumEntries(character.getEquipment().getNumGroups());

        this.renderMenu();
    }
}
