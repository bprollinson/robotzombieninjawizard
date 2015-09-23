package rznw.ui;

public class LoadScreenRenderer extends MenuScreenRenderer
{
    public LoadScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Load");
    }
}
