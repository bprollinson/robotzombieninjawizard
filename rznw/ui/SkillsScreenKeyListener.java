package rznw.ui;

import java.awt.event.KeyEvent;

public class SkillsScreenKeyListener extends StateTransitionKeyListener
{
    private SkillsScreenRenderer skillsScreenRenderer;

    public SkillsScreenKeyListener(SkillsScreenRenderer skillsScreenRenderer)
    {
        this.skillsScreenRenderer = skillsScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.skillsScreenRenderer.render();
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
