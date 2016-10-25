package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class SellEquipmentMenuKeyListener extends StateTransitionKeyListener
{
    private ShopScreenRenderer shopScreenRenderer;
    private GameWorld gameWorld;
    private MenuState subMenuState;

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
        this.subMenuState = new MenuState(equipment.getNumGroups());

        this.renderMenu();
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.subMenuState.moveUp();
                this.renderMenu();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.subMenuState.moveDown();
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
        equipment = this.getEquipmentGroups(equipment);
        if (this.subMenuState.hasEntries())
        {
            int price = equipment.getEquipmentGroup(this.subMenuState.getEntryNumber()).getItem().getSellPrice();
            priceDisplay = "Sell Price: " + price;
        }
        this.shopScreenRenderer.renderEquipmentSubMenu(this.gameWorld.getMainCharacter(), menuTitle, priceDisplay, equipment, this.subMenuState);
    }

    private void processSell()
    {
        if (!this.subMenuState.hasEntries())
        {
            return;
        }

        MainCharacter character = this.gameWorld.getMainCharacter();
        Equipment equipment = character.getEquipment();
        equipment = this.getEquipmentGroups(equipment);
        EquipmentGroup equipmentGroup = equipment.getEquipmentGroup(this.subMenuState.getEntryNumber());
        EquipmentItem item = equipmentGroup.getItem();

        boolean itemSold = false;

        if (item.isWeapon())
        {
            Weapon equippedWeapon = character.getEquipment().getEquippedWeapon();
            boolean isEquippedWeapon = equippedWeapon != null && item.getClass().equals(equippedWeapon.getClass());
            System.out.println(isEquippedWeapon + " - " + equipmentGroup.getNumItems());

            if (!isEquippedWeapon || equipmentGroup.getNumItems() > 1)
            {
                itemSold = true;
            }
        }

        if (item.isShield())
        {
            Shield equippedShield = character.getEquipment().getEquippedShield();
            boolean isEquippedShield = equippedShield != null && item.getClass().equals(equippedShield.getClass());
            System.out.println(isEquippedShield + " - " + equipmentGroup.getNumItems());

            if (!isEquippedShield || equipmentGroup.getNumItems() > 1)
            {
                itemSold = true;
            }
        }

        if (item.isArmor())
        {
            Armor equippedArmor = character.getEquipment().getEquippedArmor();
            boolean isEquippedArmor = equippedArmor != null && item.getClass().equals(equippedArmor.getClass());
            System.out.println(isEquippedArmor + " - " + equipmentGroup.getNumItems());

            if (!isEquippedArmor || equipmentGroup.getNumItems() > 1)
            {
                itemSold = true;
            }
        }

        if (itemSold)
        {
            Inventory inventory = character.getInventory();
            int goldGained = equipmentGroup.getItem().getSellPrice();
            inventory.addGold(goldGained);
            character.getEquipment().removeEquipment(item);
        }

        this.subMenuState.adjustNumEntries(character.getEquipment().getNumGroups());

        this.renderMenu();
    }

    private Equipment getEquipmentGroups(Equipment equipment)
    {
        Equipment result = new Equipment(null);

        int numGroups = equipment.getNumWeaponGroups();
        for (int i = 0; i < numGroups; i++)
        {
            try
            {
                result.addEquipment(equipment.getWeaponGroup(i));
            }
            catch (EquipmentFullException efe)
            {
            }
        }

        numGroups = equipment.getNumShieldGroups();
        for (int i = 0; i < numGroups; i++)
        {
            try
            {
                result.addEquipment(equipment.getShieldGroup(i));
            }
            catch (EquipmentFullException efe)
            {
            }
        }

        numGroups = equipment.getNumArmorGroups();
        for (int i = 0; i < numGroups; i++)
        {
            try
            {
                result.addEquipment(equipment.getArmorGroup(i));
            }
            catch (EquipmentFullException efe)
            {
            }
        }

        return result;
    }
}
