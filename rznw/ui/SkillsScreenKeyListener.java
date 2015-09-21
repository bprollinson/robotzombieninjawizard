package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SkillsScreenKeyListener implements KeyListener
{
    private SkillsScreenRenderer skillsScreenRenderer;

    public SkillsScreenKeyListener(SkillsScreenRenderer skillsScreenRenderer)
    {
        this.skillsScreenRenderer = skillsScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
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
