package rznw.ui;

import java.awt.event.KeyEvent;

public class CharacterScreenKeyListener extends StateTransitionKeyListener
{
    private CharacterScreenRenderer characterScreenRenderer;

    public CharacterScreenKeyListener(CharacterScreenRenderer characterScreenRenderer)
    {
        this.characterScreenRenderer = characterScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
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
