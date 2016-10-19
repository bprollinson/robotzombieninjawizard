package rznw.ui;

import java.awt.event.KeyEvent;

public class CharacterScreenKeyListener extends StateTransitionKeyListener
{
    private static final int KEY_I = 73;

    private CharacterScreenRenderer characterScreenRenderer;
    private MenuState state;
    private boolean showingDescription = false;

    public CharacterScreenKeyListener(CharacterScreenRenderer characterScreenRenderer)
    {
        this.characterScreenRenderer = characterScreenRenderer;
        this.state = new MenuState(17);
    }

    public void keyPressed(KeyEvent event)
    {
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
            case CharacterScreenKeyListener.KEY_I:
                this.showingDescription = !this.showingDescription;
                break;
        }

        this.characterScreenRenderer.render(this.state, this.showingDescription);
    }

    public void exitState(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.characterScreenRenderer.render(this.state, this.showingDescription);
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
