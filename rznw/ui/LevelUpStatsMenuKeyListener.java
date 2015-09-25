package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class LevelUpStatsMenuKeyListener extends StateTransitionKeyListener
{
    private LevelUpStatsMenuRenderer levelUpStatsMenuRenderer;
    private MainCharacter mainCharacter;

    public LevelUpStatsMenuKeyListener(LevelUpStatsMenuRenderer levelUpStatsMenuRenderer, MainCharacter mainCharacter)
    {
        this.levelUpStatsMenuRenderer = levelUpStatsMenuRenderer;
        this.mainCharacter = mainCharacter;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.levelUpStatsMenuRenderer.render();
    }

    public void exitState(KeyEvent event)
    {
        this.mainCharacter.setPendingLevels(0);
    }

    public int getNextState(KeyEvent event)
    {
        return DispatchKeyListener.STATE_GAME_MOTION;
    }
}
