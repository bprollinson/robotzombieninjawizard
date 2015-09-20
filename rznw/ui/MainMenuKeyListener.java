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
    }

    public void keyReleased(KeyEvent event)
    {
    }

    public void keyTyped(KeyEvent event)
    {
    }

    public void enterState()
    {
        this.mainMenuRenderer.render(state);
    }
}
