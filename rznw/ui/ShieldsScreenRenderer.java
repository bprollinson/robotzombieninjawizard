package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Shield;

public class ShieldsScreenRenderer extends MenuScreenRenderer
{
    public ShieldsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, MenuState state, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            Shield shield = (Shield)character.getEquipment().getShieldGroup(state.getEntryNumber() - 1).getItem();

            this.renderCenteredString(1, shield.getDisplayName());
            int row = 3;
            String[] stats = shield.getStats();

            for (int i = 0; i < stats.length; i++)
            {
                row += this.renderStringWithNewlines(row, stats[i]);
            }

            this.renderCenteredString(29, "Press 'i' to return to the inventory");
            this.renderCenteredString(30, "menu");
        }
        else
        {
            this.renderCenteredString(1, "Shields");

            String equippedShieldDescription = "N/A";
            EquipmentItem shield = character.getEquipment().getEquippedShield();
            if (shield != null)
            {
                equippedShieldDescription = shield.getDisplayName();
            }

            this.frame.renderDisplayString(3, 2, "Equipped: " + equippedShieldDescription);
            this.frame.renderDisplayString(5, 2, "Unequip");

            int numShieldGroups = character.getEquipment().getNumShieldGroups();

            for (int i = 0; i < numShieldGroups; i++)
            {
                EquipmentGroup shieldGroup = character.getEquipment().getShieldGroup(i);

                this.frame.renderDisplayString(6 + i, 2, shieldGroup.getDisplayString());
            }

            if (state.getEntryNumber() > 0)
            {
                this.renderCenteredString(30, "Press 'i' for shield information");
            }

            this.renderCursor(state);
        }
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(state.getEntryNumber() + 5, 0, 'X');
    }
}
