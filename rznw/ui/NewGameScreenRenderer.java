package rznw.ui;

public class NewGameScreenRenderer extends MenuScreenRenderer
{
    public NewGameScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "New Game");
    }
}
