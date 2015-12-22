package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;

public class ArmorScreenRenderer extends MenuScreenRenderer
{
    public ArmorScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, MenuState state)
    {
        this.clearScreen();
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

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(state.getEntryNumber() + 5, 0, 'X');
    }
}
