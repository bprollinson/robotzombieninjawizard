package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class ShieldsScreenKeyListener extends StateTransitionKeyListener
{
    private ShieldsScreenRenderer shieldsScreenRenderer;
    private GameWorld gameWorld;

    public ShieldsScreenKeyListener(ShieldsScreenRenderer shieldsScreenRenderer, GameWorld gameWorld)
    {
        this.shieldsScreenRenderer = shieldsScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.shieldsScreenRenderer.render();
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
