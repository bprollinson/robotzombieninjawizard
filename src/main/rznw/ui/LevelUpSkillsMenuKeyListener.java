package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.MainCharacterSkills;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class LevelUpSkillsMenuKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private LevelUpSkillsMenuRenderer levelUpSkillsMenuRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private int numPoints;
    private boolean showingDescription = false;

    public LevelUpSkillsMenuKeyListener(LevelUpSkillsMenuRenderer levelUpSkillsMenuRenderer, GameWorld gameWorld)
    {
        this.levelUpSkillsMenuRenderer = levelUpSkillsMenuRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(16);
    }

    public void keyPressed(KeyEvent event)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                if (!this.showingDescription && character.getSkills().getSkillPoints(this.state.getEntryNumber()) < MainCharacterSkills.MAX_SKILL_POINTS)
                {
                    character.getSkills().addSkillPoint(this.state.getEntryNumber());
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
            case LevelUpSkillsMenuKeyListener.KEY_I:
                this.showingDescription = !this.showingDescription;
                break;
        }

        this.levelUpSkillsMenuRenderer.render(this.state, this.numPoints, character, this.showingDescription);
    }

    public void enterState(int previousState)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        this.numPoints = character.getExperience().getPendingLevels() * MainCharacterSkills.SKILL_POINTS_PER_LEVEL;
        this.levelUpSkillsMenuRenderer.render(this.state, this.numPoints, character, this.showingDescription);
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
