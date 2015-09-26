package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class LevelUpStatsMenuKeyListener extends StateTransitionKeyListener
{
    private LevelUpStatsMenuRenderer levelUpStatsMenuRenderer;
    private MainCharacter mainCharacter;
    private int numPoints;

    public LevelUpStatsMenuKeyListener(LevelUpStatsMenuRenderer levelUpStatsMenuRenderer, MainCharacter mainCharacter)
    {
        this.levelUpStatsMenuRenderer = levelUpStatsMenuRenderer;
        this.mainCharacter = mainCharacter;
    }

    public void keyPressed(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.numPoints--;
        }

        this.levelUpStatsMenuRenderer.render(this.numPoints);
    }

    public void enterState()
    {
        this.numPoints = mainCharacter.getPendingLevels() * MainCharacter.STAT_POINTS_PER_LEVEL;
        this.levelUpStatsMenuRenderer.render(this.numPoints);
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
