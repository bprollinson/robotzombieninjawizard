package rznw.ui;

public class LoadConfirmationScreenRenderer extends MenuScreenRenderer
{
    public LoadConfirmationScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Load");

        this.renderCenteredString(3, "Game loaded!");

        this.renderCenteredString(30, "Press any key to continue...");
    }
}
