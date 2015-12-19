package rznw.ui;

import rznw.game.maincharacter.inventory.Equipment;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class ShieldsScreenKeyListener extends StateTransitionKeyListener
{
    private ShieldsScreenRenderer shieldsScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;

    public ShieldsScreenKeyListener(ShieldsScreenRenderer shieldsScreenRenderer, GameWorld gameWorld)
    {
        this.shieldsScreenRenderer = shieldsScreenRenderer;
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
                    this.gameWorld.getMainCharacter().getEquipment().unequipShield();
                }
                else
                {
                    this.gameWorld.getMainCharacter().getEquipment().equipShield(this.state.getEntryNumber() - 1);
                }

                break;
        }

        this.shieldsScreenRenderer.render(this.gameWorld.getMainCharacter(), this.state);
    }

    public void enterState(int previousState)
    {
        Equipment equipment = gameWorld.getMainCharacter().getEquipment();
        this.state = new MenuState(equipment.getNumShieldGroups());
        this.shieldsScreenRenderer.render(this.gameWorld.getMainCharacter(), this.state);
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

        return DispatchKeyListener.STATE_SHIELDS_SCREEN;
    }
}
