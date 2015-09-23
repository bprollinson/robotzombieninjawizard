package rznw.ui;

public class CharacterScreenRenderer extends MenuScreenRenderer
{
    public CharacterScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Character");
    }
}
