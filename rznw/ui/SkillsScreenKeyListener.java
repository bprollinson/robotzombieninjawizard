package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.skill.Skill;
import rznw.game.skill.SkillFactory;
import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;

import java.awt.event.KeyEvent;

public class SkillsScreenKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private SkillsScreenRenderer skillsScreenRenderer;
    private GameWorld gameWorld;
    private MainCharacterTurnHandler turnHandler;
    private MenuState state;
    private boolean skillUsed;
    private boolean showingDescription = false;

    public SkillsScreenKeyListener(SkillsScreenRenderer skillsScreenRenderer, GameWorld gameWorld, MainCharacterTurnHandler turnHandler)
    {
        this.skillsScreenRenderer = skillsScreenRenderer;
        this.gameWorld = gameWorld;
        this.turnHandler = turnHandler;
        this.state = new MenuState(15);
    }

    public void keyPressed(KeyEvent event)
    {
        MainCharacter character = this.gameWorld.getMainCharacter();

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
                if (!this.showingDescription)
                {
                    SkillFactory skillFactory = character.getSkillFactory();
                    Skill skill = skillFactory.getSkill(this.state.getEntryNumber());
                    if (skill != null && skill.canUse(gameWorld))
                    {
                        skill.use(gameWorld);
                        this.skillUsed = true;
                    }
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
            case SkillsScreenKeyListener.KEY_I:
                this.showingDescription = !this.showingDescription;
                break;
        }

        this.skillsScreenRenderer.render(this.state, this.gameWorld.getMainCharacter(), this.showingDescription);
    }

    public void enterState(int previousState)
    {
        this.skillUsed = false;

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

        if (this.skillUsed)
        {
            if (this.gameWorld.getMainCharacter().getStatusEffects().detectVitalityEnabled())
            {
                this.turnHandler.handlePostCharacterTurn();
                this.turnHandler.handleEnemyTurns();
                this.turnHandler.handlePostEnemyTurns();

                this.gameWorld.getMainCharacter().getStatusEffects().disableDetectVitality();

                return DispatchKeyListener.STATE_DETECT_VITALITY;
            }

            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_SKILLS_SCREEN;
    }
}
