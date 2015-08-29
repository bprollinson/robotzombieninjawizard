package rznw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementKeyListener implements KeyListener
{
    private GameCharacter character;
    private Map map;
    private MapRenderer renderer;

    public MovementKeyListener(GameCharacter character, Map map, MapRenderer renderer)
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
                deltaY = -1;
                break;
            case KeyEvent.VK_DOWN:
                deltaY = 1;
                break;
            case KeyEvent.VK_LEFT:
                deltaX = -1;
                break;
            case KeyEvent.VK_RIGHT:
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
