package rznw.turn.positionchange;

import java.awt.event.KeyEvent;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.MapElement;

public class KeyBasedPositionChange
{
    private int initialRow;
    private int initialColumn;

    private int deltaRow;
    private int deltaColumn;

    private int finalRow;
    private int finalColumn;

    public KeyBasedPositionChange(MainCharacter character, KeyEvent event)
    {
        MapElement mapElement = character.getMapElement();

        this.initialRow = mapElement.getRow();
        this.initialColumn = mapElement.getColumn();

        this.calculateDeltas(event);

        this.finalRow = this.initialRow + this.deltaRow;
        this.finalColumn = this.initialColumn + this.deltaColumn;
    }

    private void calculateDeltas(KeyEvent event)
    {
        this.deltaRow = 0;
        this.deltaColumn = 0;

        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.deltaRow = -1;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.deltaRow = 1;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_KP_LEFT:
                this.deltaColumn = -1;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_NUMPAD6:
            case KeyEvent.VK_KP_RIGHT:
                this.deltaColumn = 1;
                break;
            case KeyEvent.VK_NUMPAD7:
            case KeyEvent.VK_HOME:
                this.deltaRow = -1;
                this.deltaColumn = -1;
                break;
            case KeyEvent.VK_NUMPAD9:
            case KeyEvent.VK_PAGE_UP:
                this.deltaRow = -1;
                this.deltaColumn = 1;
                break;
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_END:
                this.deltaRow = 1;
                this.deltaColumn = -1;
                break;
            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_PAGE_DOWN:
                this.deltaRow = 1;
                this.deltaColumn = 1;
                break;
        }
    }

    public boolean isChange()
    {
        return this.deltaRow != 0 || this.deltaColumn != 0;
    }

    public int getInitialRow()
    {
        return this.initialRow;
    }

    public int getInitialColumn()
    {
        return this.initialColumn;
    }

    public int getFinalRow()
    {
        return this.finalRow;
    }

    public int getFinalColumn()
    {
        return this.finalColumn;
    }
}
