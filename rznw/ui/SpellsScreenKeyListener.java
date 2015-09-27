package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class SpellsScreenKeyListener extends StateTransitionKeyListener
{
    private SpellsScreenRenderer spellsScreenRenderer;
    private MainCharacter mainCharacter;

    public SpellsScreenKeyListener(SpellsScreenRenderer spellsScreenRenderer, MainCharacter mainCharacter)
    {
        this.spellsScreenRenderer = spellsScreenRenderer;
        this.mainCharacter = mainCharacter;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.spellsScreenRenderer.render(this.mainCharacter);
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
