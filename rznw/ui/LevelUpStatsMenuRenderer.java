package rznw.ui;

public class LevelUpStatsMenuRenderer extends MenuScreenRenderer
{
    public LevelUpStatsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(int numPoints)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Level Up!");
        this.renderCenteredString(3, "Points remaining: " + numPoints);
    }
}
