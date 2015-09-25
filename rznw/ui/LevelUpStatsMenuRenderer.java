package rznw.ui;

public class LevelUpStatsMenuRenderer extends MenuScreenRenderer
{
    public LevelUpStatsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Level Up!");
    }
}
