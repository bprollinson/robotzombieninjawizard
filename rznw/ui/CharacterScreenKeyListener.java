package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharacterScreenKeyListener implements KeyListener
{
    private CharacterScreenRenderer characterScreenRenderer;

    public CharacterScreenKeyListener(CharacterScreenRenderer characterScreenRenderer)
    {
        this.characterScreenRenderer = characterScreenRenderer;
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
        this.characterScreenRenderer.render();
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_CHARACTER_SCREEN;
    }
}
