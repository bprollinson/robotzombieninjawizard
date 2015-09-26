package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class LevelUpSpellsMenuKeyListener extends StateTransitionKeyListener
{
    private LevelUpSpellsMenuRenderer levelUpSpellsMenuRenderer;
    private MainCharacter mainCharacter;
    private int numPoints;

    public LevelUpSpellsMenuKeyListener(LevelUpSpellsMenuRenderer levelUpSpellsMenuRenderer, MainCharacter mainCharacter)
    {
        this.levelUpSpellsMenuRenderer = levelUpSpellsMenuRenderer;
        this.mainCharacter = mainCharacter;
    }

    public void keyPressed(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.numPoints--;
        }

        this.levelUpSpellsMenuRenderer.render(this.numPoints);
    }

    public void enterState()
    {
        this.numPoints = mainCharacter.getPendingLevels() * MainCharacter.SPELL_POINTS_PER_LEVEL;
        this.levelUpSpellsMenuRenderer.render(this.numPoints);
    }

    public void exitState(KeyEvent event)
    {
        this.mainCharacter.setPendingLevels(0);
    }

    public int getNextState(KeyEvent event)
    {
        if (this.numPoints > 0)
        {
            return DispatchKeyListener.STATE_LEVEL_UP_SPELLS_MENU;
        }

        return DispatchKeyListener.STATE_GAME_MOTION;
    }
}
