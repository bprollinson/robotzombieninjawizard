package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class LevelUpSkillsMenuKeyListener extends StateTransitionKeyListener
{
    private LevelUpSkillsMenuRenderer levelUpSkillsMenuRenderer;
    private MainCharacter mainCharacter;
    private int numPoints;

    public LevelUpSkillsMenuKeyListener(LevelUpSkillsMenuRenderer levelUpSkillsMenuRenderer, MainCharacter mainCharacter)
    {
        this.levelUpSkillsMenuRenderer = levelUpSkillsMenuRenderer;
        this.mainCharacter = mainCharacter;
    }

    public void keyPressed(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.numPoints--;
        }

        this.levelUpSkillsMenuRenderer.render(this.numPoints);
    }

    public void enterState()
    {
        this.numPoints = mainCharacter.getPendingLevels() * MainCharacter.SKILL_POINTS_PER_LEVEL;
        this.levelUpSkillsMenuRenderer.render(this.numPoints);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (this.numPoints > 0)
        {
            return DispatchKeyListener.STATE_LEVEL_UP_SKILLS_MENU;
        }

        return DispatchKeyListener.STATE_LEVEL_UP_SPELLS_MENU;
    }
}
