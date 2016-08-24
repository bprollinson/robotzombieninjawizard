package rznw.ui;

public class NewGamePlusScreenRenderer extends MenuScreenRenderer
{
    public NewGamePlusScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "New Game Plus");
    }
}
