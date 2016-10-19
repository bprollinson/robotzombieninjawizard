package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.MainCharacterSpells;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class LevelUpSpellsMenuKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private LevelUpSpellsMenuRenderer levelUpSpellsMenuRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private int numPoints;
    private boolean showingDescription = false;

    public LevelUpSpellsMenuKeyListener(LevelUpSpellsMenuRenderer levelUpSpellsMenuRenderer, GameWorld gameWorld)
    {
        this.levelUpSpellsMenuRenderer = levelUpSpellsMenuRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(16);
    }

    public void keyPressed(KeyEvent event)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                if (!this.showingDescription && character.getSpells().getSpellPoints(this.state.getEntryNumber(), false) < MainCharacterSpells.MAX_SPELL_POINTS)
                {
                    character.getSpells().addSpellPoint(this.state.getEntryNumber());
                    this.numPoints--;
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
            case LevelUpSpellsMenuKeyListener.KEY_I:
                this.showingDescription = !this.showingDescription;
                break;
        }

        this.levelUpSpellsMenuRenderer.render(this.state, this.numPoints, character, this.showingDescription);
    }

    public void enterState(int previousState)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        this.numPoints = character.getExperience().getPendingLevels() * MainCharacterSpells.SPELL_POINTS_PER_LEVEL;
        this.levelUpSpellsMenuRenderer.render(this.state, this.numPoints, character, this.showingDescription);
    }

    public void exitState(KeyEvent event)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        character.getExperience().setPendingLevels(0);
        character.resetStateAfterLevelUp();
    }

    public int getNextState(KeyEvent event)
    {
        if (this.numPoints > 0)
        {
            return DispatchKeyListener.STATE_LEVEL_UP_SPELLS_MENU;
        }

        if (this.gameWorld.gameCompleted())
        {
            return DispatchKeyListener.STATE_GAME_COMPLETED;
        }

        return DispatchKeyListener.STATE_GAME_MOTION;
    }
}
