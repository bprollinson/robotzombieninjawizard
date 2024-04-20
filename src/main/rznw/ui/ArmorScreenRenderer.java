package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;

public class ArmorScreenRenderer extends MenuScreenRenderer
{
    public ArmorScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, MenuState state, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            Armor armor = (Armor)character.getEquipment().getArmorGroup(state.getEntryNumber() - 1).getItem();

            this.renderCenteredString(1, armor.getDisplayName());
            int row = 3;
            String[] stats = armor.getStats();

            for (int i = 0; i < stats.length; i++)
            {
                row += this.renderStringWithNewlines(row, stats[i]);
            }

            this.renderCenteredString(29, "Press 'i' to return to the inventory");
            this.renderCenteredString(30, "menu");
        }
        else
        {
            this.renderCenteredString(1, "Armor");

            String equippedArmorDescription = "N/A";
            EquipmentItem armor = character.getEquipment().getEquippedArmor();
            if (armor != null)
            {
                equippedArmorDescription = armor.getDisplayName();
            }

            this.frame.renderDisplayString(3, 2, "Equipped: " + equippedArmorDescription);
            this.frame.renderDisplayString(5, 2, "Unequip");

            int numArmorGroups = character.getEquipment().getNumArmorGroups();

            for (int i = 0; i < numArmorGroups; i++)
            {
                EquipmentGroup armorGroup = character.getEquipment().getArmorGroup(i);

                this.frame.renderDisplayString(6 + i, 2, armorGroup.getDisplayString());
            }

            if (state.getEntryNumber() > 0)
            {
                this.renderCenteredString(30, "Press 'i' for armor information");
            }

            this.renderCursor(state);
        }
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(state.getEntryNumber() + 5, 0, 'X');
    }
}
