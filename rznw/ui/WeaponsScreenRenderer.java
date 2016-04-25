package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.Weapon;

public class WeaponsScreenRenderer extends MenuScreenRenderer
{
    public WeaponsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, MenuState state, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            Weapon weapon = (Weapon)character.getEquipment().getWeaponGroup(state.getEntryNumber() - 1).getItem();

            this.renderCenteredString(1, weapon.getDisplayName());
            int row = 3;
            String[] stats = weapon.getStats();

            for (int i = 0; i < stats.length; i++)
            {
                row += this.renderStringWithNewlines(row, stats[i]);
            }

            this.renderCenteredString(29, "Press 'i' to return to the inventory");
            this.renderCenteredString(30, "menu");
        }
        else
        {
            this.renderCenteredString(1, "Weapons");

            String equippedWeaponDescription = "N/A";
            EquipmentItem weapon = character.getEquipment().getEquippedWeapon();
            if (weapon != null)
            {
                equippedWeaponDescription = weapon.getDisplayName();
            }

            this.frame.renderDisplayString(3, 2, "Equipped: " + equippedWeaponDescription);
            this.frame.renderDisplayString(5, 2, "Unequip");

            int numWeaponGroups = character.getEquipment().getNumWeaponGroups();

            for (int i = 0; i < numWeaponGroups; i++)
            {
                EquipmentGroup weaponGroup = character.getEquipment().getWeaponGroup(i);

                this.frame.renderDisplayString(6 + i, 2, weaponGroup.getDisplayString());
            }

            if (state.getEntryNumber() > 0)
            {
                this.renderCenteredString(30, "Press 'i' for weapon information");
            }

            this.renderCursor(state);
        }
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(state.getEntryNumber() + 5, 0, 'X');
    }
}
