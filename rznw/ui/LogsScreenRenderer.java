package rznw.ui;

public class LogsScreenRenderer extends MenuScreenRenderer
{
    private MainGameFrame frame;

    public LogsScreenRenderer(MainGameFrame frame)
    {
        super(frame);

        this.frame = frame;
    }

    public void render()
    {
        this.clearScreen();

        this.renderCenteredString(1, "Logs");
    }
}
