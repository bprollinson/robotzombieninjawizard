package rznw.ui;

public class HotkeysScreenRenderer extends MenuScreenRenderer
{
    public HotkeysScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Hotkeys");

        this.frame.renderDisplayString(3, 0, "h - Hotkeys");
        this.frame.renderDisplayString(4, 0, "c - Character");
        this.frame.renderDisplayString(5, 0, "k - Skills");
        this.frame.renderDisplayString(6, 0, "p - Spells");
        this.frame.renderDisplayString(7, 0, "i - Inventory");
        this.frame.renderDisplayString(8, 0, "e - Equipment");
        this.frame.renderDisplayString(9, 0, "o - Logs");
        this.frame.renderDisplayString(10, 0, "t - Instructions");
        this.frame.renderDisplayString(11, 0, "s - Save");
        this.frame.renderDisplayString(12, 0, "l - Load");
        this.frame.renderDisplayString(13, 0, "n - New Game");
        this.frame.renderDisplayString(14, 0, "x - Exit");
    }
}
