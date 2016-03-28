package rznw.ui;

import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.AssassinsCloak;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Herb;
import rznw.game.maincharacter.inventory.Inventory;
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

        this.buyInventory = new Inventory();
        this.buyInventory.addItems(new InventoryItemGroup(new Potion(), 3));
        this.buyInventory.addItems(new InventoryItemGroup(new Herb(), 3));

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
                    this.inSubmenu = true;

                    if (this.topMenuState.getEntryNumber() == 0)
                    {
                        this.subMenuState = new MenuState(this.buyInventory.getNumItemGroups() - 1);
                    }

                    if (this.topMenuState.getEntryNumber() == 1)
                    {
                        this.subMenuState = new MenuState(this.buyEquipment.size() - 1);
                    }

                    if (this.topMenuState.getEntryNumber() == 2)
                    {
                        MainCharacter character = gameWorld.getMainCharacter();
                        Inventory inventory = character.getInventory();
                        this.subMenuState = new MenuState(inventory.getNumItemGroups() - 1);
                    }

                    if (this.topMenuState.getEntryNumber() == 3)
                    {
                        MainCharacter character = gameWorld.getMainCharacter();
                        Equipment equipment = character.getEquipment();
                        Vector<EquipmentGroup> equipmentGroups = this.getEquipmentGroups(equipment);
                        this.subMenuState = new MenuState(equipmentGroups.size() - 1);
                    }
                }
                else if (!this.inSubmenu)
                {
                    this.done = true;
                }
                else if (this.topMenuState.getEntryNumber() == 0)
                {
                    MainCharacter character = gameWorld.getMainCharacter();
                    InventoryItemGroup selectedGroup = this.buyInventory.getItemGroup(this.subMenuState.getEntryNumber());

                    double priceReductionPercent = 2.0 * character.getSkillPoints(4);
                    double itemCost = Math.ceil((100.0 - priceReductionPercent) / 100.0 * selectedGroup.getItem().getValue());

                    if (character.getInventory().getNumGold() >= itemCost)
                    {
                        character.getInventory().addItems(new InventoryItemGroup(selectedGroup.getItem(), 1));
                        this.buyInventory.removeItems(new InventoryItemGroup(selectedGroup.getItem(), 1));

                        character.getInventory().removeGold((int)itemCost);
                    }
                }
                else if (this.topMenuState.getEntryNumber() == 1)
                {
                    MainCharacter character = gameWorld.getMainCharacter();
                    EquipmentGroup selectedGroup = this.buyEquipment.get(this.subMenuState.getEntryNumber());

                    double priceReductionPercent = 2.0 * character.getSkillPoints(4);
                    double equipmentCost = Math.ceil((100.0 - priceReductionPercent) / 100.0 * selectedGroup.getItem().getValue());

                    if (character.getInventory().getNumGold() >= equipmentCost)
                    {
                        character.getEquipment().addEquipment(new EquipmentGroup(selectedGroup.getItem(), 1));

                        selectedGroup.removeEquipmentFromGroup(1);
                        if (selectedGroup.getNumItems() == 0)
                        {
                            this.buyEquipment.remove(this.subMenuState.getEntryNumber());
                        }

                        character.getInventory().removeGold((int)equipmentCost);
                    }
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
                else if (this.topMenuState.getEntryNumber() == 3)
                {
                    MainCharacter character = gameWorld.getMainCharacter();
                    Equipment equipment = character.getEquipment();
                    Vector<EquipmentGroup> equipmentGroups = this.getEquipmentGroups(equipment);
                    EquipmentGroup equipmentGroup = equipmentGroups.get(this.subMenuState.getEntryNumber());
                    EquipmentItem item = equipmentGroup.getItem();

                    int goldGained = 0;

                    if (item instanceof Weapon)
                    {
                        Weapon equippedWeapon = equipment.getEquippedWeapon();
                        boolean isEquippedWeapon = equippedWeapon != null && item.getClass().equals(equippedWeapon.getClass());

                        if (!isEquippedWeapon || equipmentGroup.getNumItems() > 1)
                        {
                            Inventory inventory = character.getInventory();
                            goldGained = (int)Math.floor(0.6 * equipmentGroup.getItem().getValue());
                            inventory.addGold(goldGained);
                            equipment.removeEquipment(item);
                        }
                    }

                    if (item instanceof Shield)
                    {
                        Shield equippedShield = equipment.getEquippedShield();
                        boolean isEquippedShield = equippedShield != null && item.getClass().equals(equippedShield.getClass());

                        if (!isEquippedShield || equipmentGroup.getNumItems() > 1)
                        {
                            goldGained = (int)Math.floor(0.6 * equipmentGroup.getItem().getValue());
                            equipment.removeEquipment(item);
                        }
                    }

                    if (item instanceof Armor)
                    {
                        Armor equippedArmor = equipment.getEquippedArmor();
                        boolean isEquippedArmor = equippedArmor != null && item.getClass().equals(equippedArmor.getClass());

                        if (!isEquippedArmor || equipmentGroup.getNumItems() > 1)
                        {
                            goldGained = (int)Math.floor(0.6 * equipmentGroup.getItem().getValue());
                            equipment.removeEquipment(item);
                        }
                    }
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
            String menuTitle;
            String priceDisplay = "";
            int price;
            Inventory inventory;
            double priceReductionPercent;

            switch (this.topMenuState.getEntryNumber())
            {
                case 0:
                    menuTitle = "Buy Items";
                    priceReductionPercent = 2.0 * gameWorld.getMainCharacter().getSkillPoints(4);
                    if (this.subMenuState.getEntryNumber() < this.buyInventory.getNumItemGroups())
                    {
                        price = (int)Math.ceil((100.0 - priceReductionPercent) / 100.0 * this.buyInventory.getItemGroup(this.subMenuState.getEntryNumber()).getItem().getValue());
                        priceDisplay = "Purchase Price: " + price;
                    }
                    this.shopScreenRenderer.renderInventorySubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, this.buyInventory, this.subMenuState);
                    break;
                case 1:
                    menuTitle = "Buy Equipment";
                    priceReductionPercent = 2.0 * gameWorld.getMainCharacter().getSkillPoints(4);
                    if (this.subMenuState.getEntryNumber() < this.buyEquipment.size())
                    {
                        price = (int)Math.ceil((100.0 - priceReductionPercent) / 100.0 * this.buyEquipment.get(this.subMenuState.getEntryNumber()).getItem().getValue());
                        priceDisplay = "Purchase Price: " + price;
                    }
                    this.shopScreenRenderer.renderEquipmentSubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, this.buyEquipment, this.subMenuState);
                    break;
                case 2:
                    menuTitle = "Sell Items";
                    inventory = gameWorld.getMainCharacter().getInventory();
                    if (this.subMenuState.getEntryNumber() < inventory.getNumItemGroups())
                    {
                        price = (int)Math.floor(0.6 * inventory.getItemGroup(this.subMenuState.getEntryNumber()).getItem().getValue());
                        priceDisplay = "Sell Price: " + price;
                    }
                    this.shopScreenRenderer.renderInventorySubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, inventory, this.subMenuState);
                    break;
                case 3:
                    menuTitle = "Sell Equipment";
                    Equipment equipment = gameWorld.getMainCharacter().getEquipment();
                    Vector<EquipmentGroup> equipmentGroups = this.getEquipmentGroups(equipment);
                    if (this.subMenuState.getEntryNumber() < equipmentGroups.size())
                    {
                        price = (int)Math.floor(0.6 * equipmentGroups.get(this.subMenuState.getEntryNumber()).getItem().getValue());
                        priceDisplay = "Sell Price: " + price;
                    }
                    this.shopScreenRenderer.renderEquipmentSubMenu(gameWorld.getMainCharacter(), menuTitle, priceDisplay, equipmentGroups, this.subMenuState);
                    break;
            }
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
}
