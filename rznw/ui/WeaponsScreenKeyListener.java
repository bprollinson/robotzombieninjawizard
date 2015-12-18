package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class WeaponsScreenKeyListener extends StateTransitionKeyListener
{
    private WeaponsScreenRenderer weaponsScreenRenderer;
    private GameWorld gameWorld;

    public WeaponsScreenKeyListener(WeaponsScreenRenderer weaponsScreenRenderer, GameWorld gameWorld)
    {
        this.weaponsScreenRenderer = weaponsScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.weaponsScreenRenderer.render();
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
