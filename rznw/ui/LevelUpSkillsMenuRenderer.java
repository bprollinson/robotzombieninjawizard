package rznw.ui;

public class LevelUpSkillsMenuRenderer extends MenuScreenRenderer
{
    public LevelUpSkillsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(int numPoints)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Level Up!");
        this.renderCenteredString(3, "Skill points remaining: " + numPoints);
    }
}
