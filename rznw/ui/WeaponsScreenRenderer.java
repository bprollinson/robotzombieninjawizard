package rznw.ui;

public class WeaponsScreenRenderer extends MenuScreenRenderer
{
    public WeaponsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Weapons");
    }
}
