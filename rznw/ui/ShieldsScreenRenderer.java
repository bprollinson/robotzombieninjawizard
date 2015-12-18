package rznw.ui;

public class ShieldsScreenRenderer extends MenuScreenRenderer
{
    public ShieldsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Shields");
    }
}
