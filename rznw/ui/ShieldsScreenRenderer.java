package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentGroup;

public class ShieldsScreenRenderer extends MenuScreenRenderer
{
    public ShieldsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter character, MenuState state)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Shields");

        this.frame.renderDisplayString(3, 2, "Equipped: N/A");
        this.frame.renderDisplayString(5, 2, "Unequip");

        int numShieldGroups = character.getEquipment().getNumShieldGroups();

        for (int i = 0; i < numShieldGroups; i++)
        {
            EquipmentGroup shieldGroup = character.getEquipment().getShieldGroup(i);

            this.frame.renderDisplayString(6 + i, 2, shieldGroup.getDisplayString());
        }

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        this.frame.renderDisplayCharacter(state.getEntryNumber() + 5, 0, 'X');
    }
}
