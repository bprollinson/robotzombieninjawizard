package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class DeathScreenKeyListener extends StateTransitionKeyListener
{
    private DeathScreenRenderer deathScreenRenderer;
    private GameWorld gameWorld;

    public DeathScreenKeyListener(DeathScreenRenderer deathScreenRenderer, GameWorld gameWorld)
    {
        this.deathScreenRenderer = deathScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.deathScreenRenderer.render(this.gameWorld.getMainCharacter(), this.gameWorld.getMap());
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        return DispatchKeyListener.STATE_START_SCREEN;
    }
}
