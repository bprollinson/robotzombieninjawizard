package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;

public class ShopScreenRenderer extends MenuScreenRenderer
{
    public ShopScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void renderTopMenu(MainCharacter character, MenuState topMenuState)
    {
        this.clearScreen();

        this.renderCenteredString(0, "Shop");
        this.frame.renderDisplayString(2, 2, "Gold: " + character.getInventory().getNumGold());
        this.frame.renderDisplayString(4, 2, "Buy Items");
        this.frame.renderDisplayString(5, 2, "Buy Equipment");
        this.frame.renderDisplayString(6, 2, "Sell Items");
        this.frame.renderDisplayString(7, 2, "Sell Equipment");
        this.frame.renderDisplayString(8, 2, "Exit");

        this.renderCursor(topMenuState);
    }

    public void renderInventorySubMenu(MainCharacter character, String title, String priceDisplay, Inventory inventory, MenuState subMenuState)
    {
        this.clearScreen();

        this.renderCenteredString(0, title);
        this.frame.renderDisplayString(2, 2, "Gold: " + character.getInventory().getNumGold());

        int numGroups = inventory.getNumItemGroups();
        for (int i = 0; i < numGroups; i++)
        {
            InventoryItemGroup group = inventory.getItemGroup(i);
            this.frame.renderDisplayString(4 + i, 2, group.getDisplayString());
        }

        this.frame.renderDisplayString(30, 2, priceDisplay);

        this.renderCursor(subMenuState);
    }

    public void renderEquipmentSubMenu(MainCharacter character, String title, String priceDisplay, Equipment equipment, MenuState subMenuState)
    {
        this.clearScreen();

        this.renderCenteredString(0, title);
        this.frame.renderDisplayString(2, 2, "Gold: " + character.getInventory().getNumGold());

        for (int i = 0; i < equipment.getNumGroups(); i++)
        {
            EquipmentGroup group = equipment.getEquipmentGroup(i);
            this.frame.renderDisplayString(4 + i, 2, group.getDisplayString());
        }

        this.frame.renderDisplayString(30, 2, priceDisplay);

        this.renderCursor(subMenuState);
    }

    private void renderCursor(MenuState state)
    {
        if (state.hasEntries())
        {
            this.frame.renderDisplayCharacter(state.getEntryNumber() + 4, 0, 'X');
        }
    }
}
