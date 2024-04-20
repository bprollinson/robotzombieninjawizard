package rznw.ui;

import rznw.game.maincharacter.inventory.Equipment;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class WeaponsScreenKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private WeaponsScreenRenderer weaponsScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private boolean showingDescription = false;

    public WeaponsScreenKeyListener(WeaponsScreenRenderer weaponsScreenRenderer, GameWorld gameWorld)
    {
        this.weaponsScreenRenderer = weaponsScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.state.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.state.moveDown();
                break;
            case KeyEvent.VK_ENTER:
                if (this.state.getEntryNumber() == 0)
                {
                    this.gameWorld.getMainCharacter().getEquipment().unequipWeapon();
                }
                else
                {
                    this.gameWorld.getMainCharacter().getEquipment().equipWeapon(this.state.getEntryNumber() - 1);
                }

                break;
            case WeaponsScreenKeyListener.KEY_I:
                if (this.state != null && this.state.getEntryNumber() > 0)
                {
                    this.showingDescription = !this.showingDescription;
                }
                break;
        }

        this.weaponsScreenRenderer.render(this.gameWorld.getMainCharacter(), this.state, this.showingDescription);
    }

    public void enterState(int previousState)
    {
        Equipment equipment = gameWorld.getMainCharacter().getEquipment();
        this.state = new MenuState(equipment.getNumWeaponGroups() + 1);
        this.weaponsScreenRenderer.render(this.gameWorld.getMainCharacter(), this.state, this.showingDescription);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_EQUIPMENT_SCREEN;
        }

        return DispatchKeyListener.STATE_WEAPONS_SCREEN;
    }
}
