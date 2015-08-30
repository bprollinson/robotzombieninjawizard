package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import rznw.map.Map;
import rznw.turn.MainCharacterTurnHandler;

public class MovementKeyListener implements KeyListener
{
    private MainCharacterTurnHandler turnHandler;
    private MapRenderer renderer;
    private Map map;

    public MovementKeyListener(MainCharacterTurnHandler turnHandler, MapRenderer renderer, Map map)
    {
        this.turnHandler = turnHandler;
        this.renderer = renderer;
        this.map = map;
    }

    public void keyPressed(KeyEvent event)
    {
        this.turnHandler.handleTurn(event);
        this.renderer.render(this.map);
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }
}
