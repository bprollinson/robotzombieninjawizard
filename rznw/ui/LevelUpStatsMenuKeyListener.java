package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class LevelUpStatsMenuKeyListener extends StateTransitionKeyListener
{
    private LevelUpStatsMenuRenderer levelUpStatsMenuRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private int numPoints;

    public LevelUpStatsMenuKeyListener(LevelUpStatsMenuRenderer levelUpStatsMenuRenderer, GameWorld gameWorld)
    {
        this.levelUpStatsMenuRenderer = levelUpStatsMenuRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(15);
    }

    public void keyPressed(KeyEvent event)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                character.addStatPoint(this.state.getEntryNumber());
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

        this.levelUpStatsMenuRenderer.render(this.state, this.numPoints, character);
    }

    public void enterState()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        this.numPoints = character.getPendingLevels() * MainCharacter.STAT_POINTS_PER_LEVEL;
        this.levelUpStatsMenuRenderer.render(this.state, this.numPoints, character);
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
