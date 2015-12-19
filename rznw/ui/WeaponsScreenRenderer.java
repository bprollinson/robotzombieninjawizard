package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;

public class WeaponsScreenRenderer extends MenuScreenRenderer
{
    public WeaponsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, MenuState state)
    {
        this.clearScreen();
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

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(state.getEntryNumber() + 5, 0, 'X');
    }
}
