package rznw.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainMenuKeyListener implements KeyListener
{
    private MainMenuRenderer mainMenuRenderer;
    private MainMenuState state;

    public MainMenuKeyListener(MainMenuRenderer mainMenuRenderer)
    {
        this.mainMenuRenderer = mainMenuRenderer;
        this.state = new MainMenuState();
    }

    public void keyPressed(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_KP_UP:
                this.state.moveUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_KP_DOWN:
                this.state.moveDown();
                break;
        }

        this.mainMenuRenderer.render(this.state);
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.mainMenuRenderer.render(this.state);
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
    }
}
