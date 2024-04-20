package rznw.ui;

import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InventoryItemGroup;

import rznw.game.maincharacter.MainCharacter;

public class InventoryScreenRenderer extends MenuScreenRenderer
{
    public InventoryScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, MenuState state, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            InventoryItem item = character.getInventory().getItemGroup(state.getEntryNumber()).getItem();

            this.renderCenteredString(1, item.getDisplayName());
            this.renderCenteredString(3, item.getDescription());

            this.renderCenteredString(29, "Press 'i' to return to the inventory");
            this.renderCenteredString(30, "menu");
        }
        else
        {
            this.renderCenteredString(1, "Inventory");

            int numGroups = character.getInventory().getNumItemGroups();
            for (int i = 0; i < numGroups; i++)
            {
                InventoryItemGroup group = character.getInventory().getItemGroup(i);
                this.frame.renderDisplayString(3 + i, 2, group.getDisplayString());
            }

            if (state != null)
            {
                this.renderCenteredString(30, "Press 'i' for item information");
            }

            this.renderCursor(state);
        }
    }

    private void renderCursor(MenuState state)
    {
        if (state != null)
        {
            this.frame.renderDisplayCharacter(state.getEntryNumber() + 3, 0, 'X');
        }
    }
}
