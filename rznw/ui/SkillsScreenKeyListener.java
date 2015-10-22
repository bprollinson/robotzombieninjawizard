package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class SkillsScreenKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private SkillsScreenRenderer skillsScreenRenderer;
    private GameWorld gameWorld;
    private MenuState state;
    private boolean showingDescription = false;

    public SkillsScreenKeyListener(SkillsScreenRenderer skillsScreenRenderer, GameWorld gameWorld)
    {
        this.skillsScreenRenderer = skillsScreenRenderer;
        this.gameWorld = gameWorld;
        this.state = new MenuState(15);
    }

    public void keyPressed(KeyEvent event)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();

        switch (event.getKeyCode())
        {
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
            case SkillsScreenKeyListener.KEY_I:
                this.showingDescription = !this.showingDescription;
                break;
        }

        this.skillsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.showingDescription);
    }

    public void enterState(int previousState)
    {
        this.skillsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.showingDescription);
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
