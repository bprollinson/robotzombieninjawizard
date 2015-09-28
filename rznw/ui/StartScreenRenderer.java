package rznw.ui;

public class StartScreenRenderer extends MenuScreenRenderer
{
    public StartScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();

        this.renderCenteredString(1, "Robot Zombie Ninja Wizard");
        this.renderCenteredString(4, "Load Game");
        this.renderCenteredString(6, "New Game");
        this.renderCenteredString(8, "Exit");
    }
}
