package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class LevelUpStatsMenuKeyListener extends StateTransitionKeyListener
{
    private LevelUpStatsMenuRenderer levelUpStatsMenuRenderer;
    private MainCharacter mainCharacter;
    private MenuState state;
    private int numPoints;

    public LevelUpStatsMenuKeyListener(LevelUpStatsMenuRenderer levelUpStatsMenuRenderer, MainCharacter mainCharacter)
    {
        this.levelUpStatsMenuRenderer = levelUpStatsMenuRenderer;
        this.mainCharacter = mainCharacter;
        this.state = new MenuState(15);
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                this.mainCharacter.addStatPoint(this.state.getEntryNumber());
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

        this.levelUpStatsMenuRenderer.render(this.state, this.numPoints, this.mainCharacter);
    }

    public void enterState()
    {
        this.numPoints = mainCharacter.getPendingLevels() * MainCharacter.STAT_POINTS_PER_LEVEL;
        this.levelUpStatsMenuRenderer.render(this.state, this.numPoints, this.mainCharacter);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (this.numPoints > 0)
        {
            return DispatchKeyListener.STATE_LEVEL_UP_STATS_MENU;
        }

        return DispatchKeyListener.STATE_LEVEL_UP_SKILLS_MENU;
    }
}
