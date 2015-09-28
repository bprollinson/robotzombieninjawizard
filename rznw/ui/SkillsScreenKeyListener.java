package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class SkillsScreenKeyListener extends StateTransitionKeyListener
{
    private SkillsScreenRenderer skillsScreenRenderer;
    private GameWorld gameWorld;

    public SkillsScreenKeyListener(SkillsScreenRenderer skillsScreenRenderer, GameWorld gameWorld)
    {
        this.skillsScreenRenderer = skillsScreenRenderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.skillsScreenRenderer.render(this.gameWorld.getMainCharacter());
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
