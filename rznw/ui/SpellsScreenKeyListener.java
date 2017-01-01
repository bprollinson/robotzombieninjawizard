package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.game.spell.SpellFactory;
import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;

import java.awt.event.KeyEvent;

public class SpellsScreenKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private SpellsScreenRenderer spellsScreenRenderer;
    private GameWorld gameWorld;
    private MapRenderer mapRenderer;
    private MainCharacterTurnHandler turnHandler;
    private MenuState state;
    private int previousState;
    private boolean spellCast;
    private boolean requiresDirection;
    private boolean showingDescription = false;

    public SpellsScreenKeyListener(SpellsScreenRenderer spellsScreenRenderer, GameWorld gameWorld, MapRenderer mapRenderer)
    {
        this.spellsScreenRenderer = spellsScreenRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(16);
        this.mapRenderer = mapRenderer;
        this.turnHandler = new MainCharacterTurnHandler(gameWorld, null);
    }

    public void keyPressed(KeyEvent event)
    {
        if (this.requiresDirection)
        {
            SpellFactory spellFactory = this.gameWorld.getMainCharacter().getSpellFactory();
            Spell spell = spellFactory.getSpell(this.state.getEntryNumber());
            int spellPoints = this.gameWorld.getMainCharacter().getSpells().getSpellPoints(this.state.getEntryNumber());

            switch (event.getKeyCode())
            {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_NUMPAD8:
                case KeyEvent.VK_KP_UP:
                    spell.cast(this.gameWorld, spellPoints, Spell.DIRECTION_UP);
                    this.turnHandler.handlePostTurn();
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_NUMPAD2:
                case KeyEvent.VK_KP_DOWN:
                    spell.cast(this.gameWorld, spellPoints, Spell.DIRECTION_DOWN);
                    this.turnHandler.handlePostTurn();
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_NUMPAD4:
                case KeyEvent.VK_KP_LEFT:
                    spell.cast(this.gameWorld, spellPoints, Spell.DIRECTION_LEFT);
                    this.turnHandler.handlePostTurn();
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_NUMPAD6:
                case KeyEvent.VK_KP_RIGHT:
                    spell.cast(this.gameWorld, spellPoints, Spell.DIRECTION_RIGHT);
                    this.turnHandler.handlePostTurn();
                    break;
                default:
                    return;
            }

            MainCharacter character = gameWorld.getMainCharacter();
            int MPCost = spell.getMPCost(character, spellPoints);
            character.setMP(character.getMP() - MPCost);

            return;
        }

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                if (!this.showingDescription)
                {
                    MainCharacter character = gameWorld.getMainCharacter();
                    SpellFactory spellFactory = character.getSpellFactory();
                    Spell spell = spellFactory.getSpell(this.state.getEntryNumber());
                    int spellPoints = character.getSpells().getSpellPoints(this.state.getEntryNumber());

                    if (spell != null && spell.canCast(gameWorld, spellPoints))
                    {
                        if (spell.requiresDirectionInput())
                        {
                            this.requiresDirection = true;
                            this.mapRenderer.render(this.gameWorld.getMap());
                            this.spellsScreenRenderer.renderDirectionInstructions();
                        }
                        else
                        {
                            int points = this.gameWorld.getMainCharacter().getSpells().getSpellPoints(this.state.getEntryNumber());
                            spell.cast(gameWorld, points);
                            this.turnHandler.handlePostTurn();
                            int MPCost = spell.getMPCost(character, points);
                            character.setMP(character.getMP() - MPCost);
                            this.spellCast = true;
                        }
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

        if (!this.requiresDirection)
        {
            this.spellsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.showingDescription);
        }
    }

    public void enterState(int previousState)
    {
        this.previousState = previousState;
        this.spellCast = false;
        this.requiresDirection = false;

        this.spellsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.showingDescription);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (this.gameWorld.getMainCharacter().isDead())
        {
            return DispatchKeyListener.STATE_DEATH_SCREEN;
        }

        if (this.gameWorld.getMainCharacter().getExperience().getPendingLevels() > 0)
        {
            return DispatchKeyListener.STATE_LEVEL_UP_STATS_MENU;
        }

        if (this.requiresDirection)
        {
            switch (event.getKeyCode())
            {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_NUMPAD8:
                case KeyEvent.VK_KP_UP:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_NUMPAD2:
                case KeyEvent.VK_KP_DOWN:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_NUMPAD4:
                case KeyEvent.VK_KP_LEFT:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_NUMPAD6:
                case KeyEvent.VK_KP_RIGHT:
                    return DispatchKeyListener.STATE_GAME_MOTION;
                default:
                    return DispatchKeyListener.STATE_SPELLS_SCREEN;
            }
        }

        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return this.previousState;
        }

        if (this.gameWorld.gameCompleted())
        {
            return DispatchKeyListener.STATE_GAME_COMPLETED;
        }

        if (this.spellCast)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_SPELLS_SCREEN;
    }
}
