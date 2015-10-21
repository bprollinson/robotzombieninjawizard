package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class SpellsScreenKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private SpellsScreenRenderer spellsScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private boolean spellCast;
    private boolean showingDescription = false;

    public SpellsScreenKeyListener(SpellsScreenRenderer spellsScreenRenderer, GameWorld gameWorld)
    {
        this.spellsScreenRenderer = spellsScreenRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(15);
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                if (!this.showingDescription)
                {
                    MainCharacter character = gameWorld.getMainCharacter();
                    SpellFactory spellFactory = character.getSpellFactory();
                    Spell spell = spellFactory.getSpell(this.state.getEntryNumber());
                    if (spell != null && spell.canCast(character))
                    {
                        spell.cast(character);
                        this.spellCast = true;
                    }
                }
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                if (!this.showingDescription)
                {
                    this.state.moveUp();
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                if (!this.showingDescription)
                {
                    this.state.moveDown();
                }
                break;
            case SpellsScreenKeyListener.KEY_I:
                this.showingDescription = !this.showingDescription;
                break;
        }

        this.spellsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.showingDescription);
    }

    public void enterState(int previousState)
    {
        this.spellCast = false;

        this.spellsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.showingDescription);
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

        if (this.spellCast)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_SPELLS_SCREEN;
    }
}
