package rznw.ui;

public class LevelUpSpellsMenuRenderer extends MenuScreenRenderer
{
    public LevelUpSpellsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(int numPoints)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Level Up!");
        this.renderCenteredString(3, "Spell points remaining: " + numPoints);
    }
}
