package rznw.ui;

import rznw.game.maincharacter.inventory.InventoryItem;

import rznw.game.maincharacter.MainCharacter;

public class InventoryScreenRenderer extends MenuScreenRenderer
{
    public InventoryScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Inventory");

        int numItems = character.getInventory().getNumItems();
        for (int i = 0; i < numItems && i < 25; i++)
        {
            InventoryItem item = character.getInventory().getItem(i);
            this.frame.renderDisplayString(3 + i, 2, item.getDisplayName());
        }
    }
}
