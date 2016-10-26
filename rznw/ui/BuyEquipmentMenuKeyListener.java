package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class BuyEquipmentMenuKeyListener extends StateTransitionKeyListener
{
    private ShopScreenRenderer shopScreenRenderer;
    private GameWorld gameWorld;
    private Equipment buyEquipment;
    private MenuState state;

    public BuyEquipmentMenuKeyListener(ShopScreenRenderer shopScreenRenderer, GameWorld gameWorld)
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

        return DispatchKeyListener.STATE_BUY_EQUIPMENT_MENU;
    }

    public void enterState(int previousState)
    {
        this.buyEquipment = this.gameWorld.getShopInventory().getRandomEquipments();
        this.state = new MenuState(this.buyEquipment.getNumGroups());

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
        String menuTitle = "Buy Equipment";
        String priceDisplay = "";
        if (this.state.hasEntries())
        {
            int price = this.buyEquipment.getEquipmentGroup(this.state.getEntryNumber()).getItem().getBuyPrice(this.gameWorld.getMainCharacter());
            priceDisplay = "Purchase Price: " + price;
        }
        this.shopScreenRenderer.renderEquipmentSubMenu(this.gameWorld.getMainCharacter(), menuTitle, priceDisplay, this.buyEquipment, this.state);
    }

    private void processBuy()
    {
        if (!this.state.hasEntries())
        {
            return;
        }

        MainCharacter character = this.gameWorld.getMainCharacter();
        EquipmentGroup selectedGroup = this.buyEquipment.getEquipmentGroup(this.state.getEntryNumber());

        int equipmentCost = selectedGroup.getItem().getBuyPrice(character);

        if (character.getInventory().getNumGold() >= equipmentCost)
        {
            try
            {
                EquipmentGroup group = new EquipmentGroup(selectedGroup.getItem(), 1);
                character.getEquipment().addEquipment(group);
                this.buyEquipment.removeEquipment(group.getItem());

                character.getInventory().removeGold(equipmentCost);
                this.state.adjustNumEntries(this.buyEquipment.getNumGroups());
            }
            catch (EquipmentFullException efe)
            {
                System.out.println("Equipment full");
            }

            this.renderMenu();
        }
    }
}
