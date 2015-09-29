package rznw.ui;

import java.awt.event.KeyEvent;

import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;

public class MovementKeyListener extends StateTransitionKeyListener
{
    private MainCharacterTurnHandler turnHandler;
    private MapRenderer renderer;
    private GameWorld gameWorld;

    public MovementKeyListener(MainCharacterTurnHandler turnHandler, MapRenderer renderer, GameWorld gameWorld)
    {
        this.turnHandler = turnHandler;
        this.renderer = renderer;
        this.gameWorld = gameWorld;
    }

    public void keyPressed(KeyEvent event)
    {
        this.turnHandler.handleTurn(event);
        this.renderer.render(this.gameWorld.getMap());
    }

    public void enterState(int previousState)
    {
        this.renderer.render(this.gameWorld.getMap());
        this.turnHandler.renderSummary();
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (this.gameWorld.getMainCharacter().getPendingLevels() > 0)
        {
            return DispatchKeyListener.STATE_LEVEL_UP_STATS_MENU;
        }

        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_GAME_MOTION;
    }
}
