package rznw.ui;

public class InventoryScreenRenderer extends MenuScreenRenderer
{
    public InventoryScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Inventory");
    }
}
