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

        for (int i = 0; i < 10; i++)
        {
            this.renderCenteredString(4 + 2 * i, "Slot " + (i + 1));
        }
    }
}
