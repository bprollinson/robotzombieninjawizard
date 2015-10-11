package rznw.ui;

import rznw.game.maincharacter.inventory.InventoryItemGroup;

import rznw.game.maincharacter.MainCharacter;

public class InventoryScreenRenderer extends MenuScreenRenderer
{
    public InventoryScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, MenuState state)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Inventory");

        int numGroups = character.getInventory().getNumItemGroups();
        for (int i = 0; i < numGroups; i++)
        {
            InventoryItemGroup group = character.getInventory().getItemGroup(i);
            this.frame.renderDisplayString(3 + i, 2, group.getDisplayString());
        }

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        if (state != null)
        {
            this.frame.renderDisplayCharacter(state.getEntryNumber() + 3, 0, 'X');
        }
    }
}
