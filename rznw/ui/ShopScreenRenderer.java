package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
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

    public void renderSubMenu(MainCharacter character, String title, Inventory inventory, MenuState subMenuState)
    {
        this.clearScreen();

        this.renderCenteredString(0, title);
        this.frame.renderDisplayString(2, 2, "Gold: " + character.getInventory().getNumGold());

        int numGroups = inventory.getNumItemGroups();
        for (int i = 0; i < numGroups; i++)
        {
            InventoryItemGroup group = character.getInventory().getItemGroup(i);
            this.frame.renderDisplayString(4 + i, 2, group.getDisplayString());
        }

        this.renderCursor(subMenuState);
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(state.getEntryNumber() + 4, 0, 'X');
    }
}
