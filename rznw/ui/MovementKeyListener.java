package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import rznw.map.GameWorld;
import rznw.turn.MainCharacterTurnHandler;

public class MovementKeyListener implements KeyListener
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

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }
}
