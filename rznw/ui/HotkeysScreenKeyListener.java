package rznw.ui;

import java.awt.event.KeyEvent;

public class HotkeysScreenKeyListener extends StateTransitionKeyListener
{
    private HotkeysScreenRenderer hotkeysScreenRenderer;

    public HotkeysScreenKeyListener(HotkeysScreenRenderer hotkeysScreenRenderer)
    {
        this.hotkeysScreenRenderer = hotkeysScreenRenderer;
    }

    public void keyPressed(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.hotkeysScreenRenderer.render();
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_MOTION;
        }

        return DispatchKeyListener.STATE_HOTKEYS_SCREEN;
    }
}
