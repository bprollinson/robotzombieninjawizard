package rznw.ui;

import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.AssassinsCloak;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.maincharacter.inventory.WoodenShield;
import rznw.game.maincharacter.inventory.WoodenSword;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;

import java.awt.event.KeyEvent;
import java.util.Vector;

public class ShopScreenKeyListener extends StateTransitionKeyListener
{
    private ShopScreenRenderer shopScreenRenderer;
    private GameWorld gameWorld;
    private MainCharacterTurnHandler turnHandler;
    private MenuState topMenuState;
    private MenuState subMenuState;
    private boolean inSubmenu;
    private boolean done;
    private Inventory buyInventory;
    private Vector<EquipmentGroup> buyEquipment;

    public ShopScreenKeyListener(ShopScreenRenderer shopScreenRenderer, GameWorld gameWorld, MainCharacterTurnHandler turnHandler)
    {
        this.shopScreenRenderer = shopScreenRenderer;
        this.gameWorld = gameWorld;
        this.turnHandler = turnHandler;
        this.topMenuState = new MenuState(4);
        this.subMenuState = new MenuState(0);
    }

    public void enterState(int previousState)
    {
        this.inSubmenu = false;
        this.done = false;

        this.shopScreenRenderer.renderTopMenu(gameWorld.getMainCharacter(), topMenuState);

        this.buyInventory = new Inventory(null);
        try
        {
            this.buyInventory.addItems(new InventoryItemGroup(new Potion(), 3));
            this.buyInventory.addItems(new InventoryItemGroup(new Herb(), 3));
        }
        catch (InventoryFullException ife)
        {
        }

        this.buyEquipment = new Vector<EquipmentGroup>();
        this.buyEquipment.add(new EquipmentGroup(new AssassinsCloak(), 1));
        this.buyEquipment.add(new EquipmentGroup(new WoodenShield(), 1));
        this.buyEquipment.add(new EquipmentGroup(new WoodenSword(), 1));
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
                else
                {
                    this.subMenuState.moveUp();
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                if (!this.inSubmenu)
                {
                    this.topMenuState.moveDown();
                }
                else
                {
                    this.subMenuState.moveDown();
                }
                break;
            case KeyEvent.VK_ENTER:
                if (!this.inSubmenu && this.topMenuState.getEntryNumber() <= 3)
                {
                    this.moveIntoSubmenu();
                }
                else if (!this.inSubmenu)
                {
                    this.done = true;
                }
                else
                {
                    this.processBuySell();
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
            this.renderSubMenu();
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

    private Vector<EquipmentGroup> getEquipmentGroups(Equipment equipment)
    {
        Vector<EquipmentGroup> result = new Vector<EquipmentGroup>();

        int numGroups = equipment.getNumWeaponGroups();
        for (int i = 0; i < numGroups; i++)
        {
            result.add(equipment.getWeaponGroup(i));
        }

        numGroups = equipment.getNumShieldGroups();
        for (int i = 0; i < numGroups; i++)
        {
            result.add(equipment.getShieldGroup(i));
        }

        numGroups = equipment.getNumArmorGroups();
        for (int i = 0; i < numGroups; i++)
        {
            result.add(equipment.getArmorGroup(i));
        }

        return result;
    }

    private void moveIntoSubmenu()
    {
        this.inSubmenu = true;
        MainCharacter character;

        switch (this.topMenuState.getEntryNumber())
        {
            case 0:
                this.subMenuState = new MenuState(this.buyInventory.getNumItemGroups() - 1);
                break;
            case 1:
                this.subMenuState = new MenuState(this.buyEquipment.size() - 1);
                break;
            case 2:
                character = gameWorld.getMainCharacter();
                Inventory inventory = character.getInventory();
                this.subMenuState = new MenuState(inventory.getNumItemGroups() - 1);            
                break;
            case 3:
                character = gameWorld.getMainCharacter();
                Equipment equipment = character.getEquipment();
                Vector<EquipmentGroup> equipmentGroups = this.getEquipmentGroups(equipment);
                this.subMenuState = new MenuState(equipmentGroups.size() - 1);
                break;
        }
    }

    private void renderSubMenu()
    {
        String menuTitle;
        String priceDisplay = "";
        int price;
        Inventory inventory;

        switch (this.topMenuState.getEntryNumber())
        {
            case 0:
                menuTitle = "Buy Items";
                if (this.subMenuState.getEntryNumber() >= 0 && this.subMenuState.getMaxEntryNumber() >= 0)
                {
                    price = this.buyInventory.getItemGroup(this.subMenuState.getEntryNumber()).getItem().getBuyPrice(this.gameWorld.getMainCharacter());
                    priceDisplay = "Purchase Price: " + price;
                }
                this.shopScreenRenderer.renderInventorySubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, this.buyInventory, this.subMenuState);
                break;
            case 1:
                menuTitle = "Buy Equipment";
                if (this.subMenuState.getEntryNumber() >= 0 && this.subMenuState.getMaxEntryNumber() >= 0)
                {
                    price = this.buyEquipment.get(this.subMenuState.getEntryNumber()).getItem().getBuyPrice(this.gameWorld.getMainCharacter());
                    priceDisplay = "Purchase Price: " + price;
                }
                this.shopScreenRenderer.renderEquipmentSubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, this.buyEquipment, this.subMenuState);
                break;
            case 2:
                menuTitle = "Sell Items";
                inventory = gameWorld.getMainCharacter().getInventory();
                if (this.subMenuState.getEntryNumber() >= 0 && this.subMenuState.getMaxEntryNumber() >= 0)
                {
                    price = inventory.getItemGroup(this.subMenuState.getEntryNumber()).getItem().getSellPrice();
                    priceDisplay = "Sell Price: " + price;
                }
                this.shopScreenRenderer.renderInventorySubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, inventory, this.subMenuState);
                break;
            case 3:
                menuTitle = "Sell Equipment";
                Equipment equipment = gameWorld.getMainCharacter().getEquipment();
                Vector<EquipmentGroup> equipmentGroups = this.getEquipmentGroups(equipment);
                if (this.subMenuState.getEntryNumber() >= 0 && this.subMenuState.getMaxEntryNumber() >= 0)
                {
                    price = equipmentGroups.get(this.subMenuState.getEntryNumber()).getItem().getSellPrice();
                    priceDisplay = "Sell Price: " + price;
                }
                this.shopScreenRenderer.renderEquipmentSubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, equipmentGroups, this.subMenuState);
                break;
            }
    }

    private void processBuySell()
    {
        if (this.subMenuState.getEntryNumber() < 0)
        {
            return;
        }

        if (this.topMenuState.getEntryNumber() == 0)
        {
            MainCharacter character = gameWorld.getMainCharacter();
            InventoryItemGroup selectedGroup = this.buyInventory.getItemGroup(this.subMenuState.getEntryNumber());

            int itemCost = selectedGroup.getItem().getBuyPrice(character);

            if (character.getInventory().getNumGold() >= itemCost)
            {
                try
                {
                    character.getInventory().addItems(new InventoryItemGroup(selectedGroup.getItem(), 1));
                    this.buyInventory.removeItems(new InventoryItemGroup(selectedGroup.getItem(), 1));

                    character.getInventory().removeGold((int)itemCost);

                    this.subMenuState.adjustMaxEntryNumber(this.buyInventory.getNumItemGroups() - 1);
                }
                catch (InventoryFullException ife)
                {
                    System.out.println("Inventory full");
                }
            }
        }
        else if (this.topMenuState.getEntryNumber() == 1)
        {
            MainCharacter character = gameWorld.getMainCharacter();
            EquipmentGroup selectedGroup = this.buyEquipment.get(this.subMenuState.getEntryNumber());

            int equipmentCost = selectedGroup.getItem().getBuyPrice(character);

            if (character.getInventory().getNumGold() >= equipmentCost)
            {
                try
                {
                    character.getEquipment().addEquipment(new EquipmentGroup(selectedGroup.getItem(), 1));

                    selectedGroup.removeEquipmentFromGroup(1);
                    if (selectedGroup.getNumItems() == 0)
                    {
                        this.buyEquipment.remove(this.subMenuState.getEntryNumber());
                    }

                    character.getInventory().removeGold((int)equipmentCost);

                    this.subMenuState.adjustMaxEntryNumber(this.buyEquipment.size() - 1);
                }
                catch (EquipmentFullException efe)
                {
                    System.out.println("Equipment full");
                }
            }
        }
        else if (this.topMenuState.getEntryNumber() == 2)
        {
            MainCharacter character = gameWorld.getMainCharacter();

            Inventory inventory = character.getInventory();
            InventoryItemGroup existingGroup = inventory.getItemGroup(this.subMenuState.getEntryNumber());
            InventoryItemGroup removalGroup = new InventoryItemGroup(existingGroup.getItem(), 1);
            inventory.removeItems(removalGroup);

            int goldGained = existingGroup.getItem().getSellPrice();
            inventory.addGold(goldGained);

            this.subMenuState.adjustMaxEntryNumber(inventory.getNumItemGroups() - 1);
        }
        else if (this.topMenuState.getEntryNumber() == 3)
        {
            MainCharacter character = gameWorld.getMainCharacter();
            Equipment equipment = character.getEquipment();
            Vector<EquipmentGroup> equipmentGroups = this.getEquipmentGroups(equipment);
            EquipmentGroup equipmentGroup = equipmentGroups.get(this.subMenuState.getEntryNumber());
            EquipmentItem item = equipmentGroup.getItem();

            boolean itemSold = false;

            if (item.isWeapon())
            {
                Weapon equippedWeapon = equipment.getEquippedWeapon();
                boolean isEquippedWeapon = equippedWeapon != null && item.getClass().equals(equippedWeapon.getClass());

                if (!isEquippedWeapon || equipmentGroup.getNumItems() > 1)
                {
                    itemSold = true;
                }
            }

            if (item.isShield())
            {
                Shield equippedShield = equipment.getEquippedShield();
                boolean isEquippedShield = equippedShield != null && item.getClass().equals(equippedShield.getClass());

                if (!isEquippedShield || equipmentGroup.getNumItems() > 1)
                {
                    itemSold = true;
                }
            }

            if (item.isArmor())
            {
                Armor equippedArmor = equipment.getEquippedArmor();
                boolean isEquippedArmor = equippedArmor != null && item.getClass().equals(equippedArmor.getClass());

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
                equipment.removeEquipment(item);
            }

            equipmentGroups = this.getEquipmentGroups(equipment);
            this.subMenuState.adjustMaxEntryNumber(equipmentGroups.size() - 1);
        }
    }
}
