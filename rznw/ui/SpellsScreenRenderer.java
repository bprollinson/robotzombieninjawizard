package rznw.ui;

public class SpellsScreenRenderer extends MenuScreenRenderer
{
    public SpellsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Spells");
    }
}
