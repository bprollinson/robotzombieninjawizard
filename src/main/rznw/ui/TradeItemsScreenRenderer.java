package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;

import java.util.HashMap;

public class TradeItemsScreenRenderer extends MenuScreenRenderer
{
    public TradeItemsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, MainCharacter character, HashMap<Integer, Integer> selectionMap)
    {
        this.clearScreen();

        Inventory inventory = character.getInventory();
        for (int i = 0; i < inventory.getNumItemGroups(); i++)
        {
            InventoryItemGroup group = inventory.getItemGroup(i);
            int displayNumber = group.getNumItems() - selectionMap.get(i);
            this.frame.renderDisplayString(i, 2, displayNumber + " " + group.getItem().getDisplayName());
        }

        this.frame.renderDisplayString(31, 0, "Enter to trade, or escape to cancel");

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(state.getEntryNumber(), 0, 'X');
    }
}
