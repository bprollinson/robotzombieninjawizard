package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class SpellsScreenKeyListener extends StateTransitionKeyListener
{
    private SpellsScreenRenderer spellsScreenRenderer;
    private GameWorld gameWorld;

    public SpellsScreenKeyListener(SpellsScreenRenderer spellsScreenRenderer, GameWorld gameWorld)
    {
        this.spellsScreenRenderer = spellsScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.spellsScreenRenderer.render(this.gameWorld.getMainCharacter());
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_SPELLS_SCREEN;
    }
}
