package rznw.ui;

import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.RandomInventoryGenerator;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.maincharacter.inventory.Weapon;
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
    private Inventory buyInventory;
    private Equipment buyEquipment;

    public ShopScreenKeyListener(ShopScreenRenderer shopScreenRenderer, GameWorld gameWorld, MainCharacterTurnHandler turnHandler)
    {
        this.shopScreenRenderer = shopScreenRenderer;
        this.gameWorld = gameWorld;
        this.turnHandler = turnHandler;
        this.topMenuState = new MenuState(5);
        this.subMenuState = new MenuState(1);
    }

    public void enterState(int previousState)
    {
        this.inSubmenu = false;
        this.done = false;

        this.shopScreenRenderer.renderTopMenu(gameWorld.getMainCharacter(), topMenuState);

        RandomInventoryGenerator.handleRegeneration(gameWorld);

        this.buyInventory = gameWorld.getShopInventory().getRandomItems();
        this.buyEquipment = gameWorld.getShopInventory().getRandomEquipments();
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
        if (event.getKeyCode() == KeyEvent.VK_ENTER && this.topMenuState.getEntryNumber() == 0)
        {
            return DispatchKeyListener.STATE_BUY_ITEMS_MENU;
        }

        if (this.done)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_SHOP;
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

    private void moveIntoSubmenu()
    {
        this.inSubmenu = true;
        MainCharacter character;

        switch (this.topMenuState.getEntryNumber())
        {
            case 1:
                this.subMenuState = new MenuState(this.buyEquipment.getNumGroups());
                break;
            case 2:
                character = gameWorld.getMainCharacter();
                Inventory inventory = character.getInventory();
                this.subMenuState = new MenuState(inventory.getNumItemGroups());
                break;
            case 3:
                character = gameWorld.getMainCharacter();
                Equipment equipment = character.getEquipment();
                this.subMenuState = new MenuState(equipment.getNumGroups());
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
            case 1:
                menuTitle = "Buy Equipment";
                if (this.subMenuState.hasEntries())
                {
                    price = this.buyEquipment.getEquipmentGroup(this.subMenuState.getEntryNumber()).getItem().getBuyPrice(this.gameWorld.getMainCharacter());
                    priceDisplay = "Purchase Price: " + price;
                }
                this.shopScreenRenderer.renderEquipmentSubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, this.buyEquipment, this.subMenuState);
                break;
            case 2:
                menuTitle = "Sell Items";
                inventory = gameWorld.getMainCharacter().getInventory();
                if (this.subMenuState.hasEntries())
                {
                    price = inventory.getItemGroup(this.subMenuState.getEntryNumber()).getItem().getSellPrice();
                    priceDisplay = "Sell Price: " + price;
                }
                this.shopScreenRenderer.renderInventorySubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, inventory, this.subMenuState);
                break;
            case 3:
                menuTitle = "Sell Equipment";
                Equipment equipment = gameWorld.getMainCharacter().getEquipment();
                equipment = this.getEquipmentGroups(equipment);
                if (this.subMenuState.hasEntries())
                {
                    price = equipment.getEquipmentGroup(this.subMenuState.getEntryNumber()).getItem().getSellPrice();
                    priceDisplay = "Sell Price: " + price;
                }
                this.shopScreenRenderer.renderEquipmentSubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, equipment, this.subMenuState);
                break;
            }
    }

    private void processBuySell()
    {
        if (!this.subMenuState.hasEntries())
        {
            return;
        }

        if (this.topMenuState.getEntryNumber() == 1)
        {
            MainCharacter character = gameWorld.getMainCharacter();
            EquipmentGroup selectedGroup = this.buyEquipment.getEquipmentGroup(this.subMenuState.getEntryNumber());

            int equipmentCost = selectedGroup.getItem().getBuyPrice(character);

            if (character.getInventory().getNumGold() >= equipmentCost)
            {
                try
                {
                    EquipmentGroup group = new EquipmentGroup(selectedGroup.getItem(), 1);
                    character.getEquipment().addEquipment(group);
                    this.buyEquipment.removeEquipment(group.getItem());

                    character.getInventory().removeGold((int)equipmentCost);
                    this.subMenuState.adjustNumEntries(this.buyEquipment.getNumGroups());
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

            this.subMenuState.adjustNumEntries(inventory.getNumItemGroups());
        }
        else if (this.topMenuState.getEntryNumber() == 3)
        {
            MainCharacter character = gameWorld.getMainCharacter();
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
        }
    }
}
