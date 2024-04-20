package rznw.ui;

public class GameCompletedScreenRenderer extends MenuScreenRenderer
{
    public GameCompletedScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Congratulations!");
        this.renderCenteredString(3, "You have defeated the evil wizard Zenith");
        this.renderCenteredString(4, "and restored peaced to the world!");
        this.renderCenteredString(30, "Press Enter to continue...");
    }
}
