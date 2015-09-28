package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class LevelUpSkillsMenuKeyListener extends StateTransitionKeyListener
{
    private LevelUpSkillsMenuRenderer levelUpSkillsMenuRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private int numPoints;

    public LevelUpSkillsMenuKeyListener(LevelUpSkillsMenuRenderer levelUpSkillsMenuRenderer, GameWorld gameWorld)
    {
        this.levelUpSkillsMenuRenderer = levelUpSkillsMenuRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(15);
    }

    public void keyPressed(KeyEvent event)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                character.addSkillPoint(this.state.getEntryNumber());
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

        this.levelUpSkillsMenuRenderer.render(this.state, this.numPoints, character);
    }

    public void enterState()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        this.numPoints = character.getPendingLevels() * MainCharacter.SKILL_POINTS_PER_LEVEL;
        this.levelUpSkillsMenuRenderer.render(this.state, this.numPoints, character);
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
