package rznw.ui;

public class SaveConfirmationScreenRenderer extends MenuScreenRenderer
{
    public SaveConfirmationScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Save");

        this.renderCenteredString(3, "Game saved!");

        this.renderCenteredString(30, "Press any key to continue...");
    }
}
