package rznw.ui;

public class SaveScreenRenderer extends MenuScreenRenderer
{
    public SaveScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Save");
    }
}
