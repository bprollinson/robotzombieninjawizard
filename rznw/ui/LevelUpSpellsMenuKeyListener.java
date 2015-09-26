package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class LevelUpSpellsMenuKeyListener extends StateTransitionKeyListener
{
    private LevelUpSpellsMenuRenderer levelUpSpellsMenuRenderer;
    private MainCharacter mainCharacter;
    private MenuState state;
    private int numPoints;

    public LevelUpSpellsMenuKeyListener(LevelUpSpellsMenuRenderer levelUpSpellsMenuRenderer, MainCharacter mainCharacter)
    {
        this.levelUpSpellsMenuRenderer = levelUpSpellsMenuRenderer;
        this.mainCharacter = mainCharacter;
        this.state = new MenuState(15);
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                this.mainCharacter.addSpellPoint(this.state.getEntryNumber());
                this.numPoints--;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.state.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.state.moveDown();
                break;
        }

        this.levelUpSpellsMenuRenderer.render(this.state, this.numPoints, this.mainCharacter);
    }

    public void enterState()
    {
        this.numPoints = mainCharacter.getPendingLevels() * MainCharacter.SPELL_POINTS_PER_LEVEL;
        this.levelUpSpellsMenuRenderer.render(this.state, this.numPoints, this.mainCharacter);
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
