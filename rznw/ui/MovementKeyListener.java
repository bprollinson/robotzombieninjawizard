package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class MovementKeyListener implements KeyListener
{
    private MainCharacter character;
    private Map map;
    private MapRenderer renderer;

    public MovementKeyListener(MainCharacter character, Map map, MapRenderer renderer)
    {
        this.character = character;
        this.map = map;
        this.renderer = renderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void keyReleased(KeyEvent event)
    {
        MapElement mapElement = this.character.getMapElement();

        int deltaX = 0;
        int deltaY = 0;

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                deltaY = -1;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                deltaY = 1;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_KP_LEFT:
                deltaX = -1;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_NUMPAD6:
            case KeyEvent.VK_KP_RIGHT:
                deltaX = 1;
                break;
            case KeyEvent.VK_NUMPAD7:
            case KeyEvent.VK_HOME:
                deltaY = -1;
                deltaX = -1;
                break;
            case KeyEvent.VK_NUMPAD9:
            case KeyEvent.VK_PAGE_UP:
                deltaY = -1;
                deltaX = 1;
                break;
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_END:
                deltaY = 1;
                deltaX = -1;
                break;
            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_PAGE_DOWN:
                deltaY = 1;
                deltaX = 1;
                break;
            default:
                return;
        }

        int oldX = mapElement.getColumn();
        int oldY = mapElement.getRow();
        int newX = oldX + deltaX;
        int newY = oldY + deltaY;

        MapElement collisionTest = map.getElement(newY, newX);
        if (collisionTest != null)
        {
            return;
        }

        map.setElement(oldY, oldX, null);
        map.setElement(newY, newX, mapElement);
        mapElement.setRow(newY);
        mapElement.setColumn(newX);
        this.renderer.render(this.map);
    }

    public void keyTyped(KeyEvent event)
    {
    }
}
