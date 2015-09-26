package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

import java.awt.event.KeyEvent;

public class SkillsScreenKeyListener extends StateTransitionKeyListener
{
    private SkillsScreenRenderer skillsScreenRenderer;
    private MainCharacter mainCharacter;

    public SkillsScreenKeyListener(SkillsScreenRenderer skillsScreenRenderer, MainCharacter mainCharacter)
    {
        this.skillsScreenRenderer = skillsScreenRenderer;
        this.mainCharacter = mainCharacter;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.skillsScreenRenderer.render(this.mainCharacter);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_SKILLS_SCREEN;
    }
}
