package rznw.ui;

public class SkillsScreenRenderer extends MenuScreenRenderer
{
    public SkillsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Skills");
    }
}
