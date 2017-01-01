package rznw.ui;

import rznw.map.GameWorld;

import java.awt.event.KeyEvent;

public class LogsScreenKeyListener extends StateTransitionKeyListener
{
    private LogsScreenRenderer logsScreenRenderer;
    private MenuState state;
    private int previousState;

    public LogsScreenKeyListener(LogsScreenRenderer logsScreenRenderer)
    {
        this.logsScreenRenderer = logsScreenRenderer;
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

        this.logsScreenRenderer.render(this.state, LogRendererFactory.instance().getLines());
    }

    public void exitState(KeyEvent event)
    {
    }

    public void enterState(int previousState)
    {
        this.previousState = previousState;
        int numLogs = LogRendererFactory.instance().getLines().size();
        int numPages = (int)Math.ceil((double)numLogs / LogsScreenRenderer.LOGS_PER_PAGE);
        this.state = new MenuState(numPages);

        this.logsScreenRenderer.render(this.state, LogRendererFactory.instance().getLines());
    }

    public int getNextState(KeyEvent event)
    {
        if (event.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            return this.previousState;
        }

        return DispatchKeyListener.STATE_LOGS_SCREEN;
    }
}
