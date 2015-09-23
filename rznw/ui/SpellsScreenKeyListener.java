package rznw.ui;

import java.awt.event.KeyEvent;

public class SpellsScreenKeyListener extends StateTransitionKeyListener
{
    private SpellsScreenRenderer spellsScreenRenderer;

    public SpellsScreenKeyListener(SpellsScreenRenderer spellsScreenRenderer)
    {
        this.spellsScreenRenderer = spellsScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.spellsScreenRenderer.render();
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
