package rznw.ui;

import rznw.ui.instructions.InstructionsFactory;

import java.awt.event.KeyEvent;

public class InstructionsScreenKeyListener extends StateTransitionKeyListener
{
    private InstructionsScreenRenderer instructionsScreenRenderer;
    private MenuState state;

    public InstructionsScreenKeyListener(InstructionsScreenRenderer instructionsScreenRenderer)
    {
        this.instructionsScreenRenderer = instructionsScreenRenderer;
        this.state = new MenuState(InstructionsFactory.NUM_PAGES);
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

        this.instructionsScreenRenderer.render(this.state);
    }

    public void enterState(int previousState)
    {
        this.instructionsScreenRenderer.render(this.state);
    }

    public void exitState(KeyEvent event)
    {
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return DispatchKeyListener.STATE_GAME_ESCAPE_MENU;
        }

        return DispatchKeyListener.STATE_INSTRUCTIONS_SCREEN;
    }
}
