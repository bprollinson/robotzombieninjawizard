package rznw.ui;

public class EquipmentScreenRenderer extends MenuScreenRenderer
{
    public EquipmentScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render()
    {
        this.clearScreen();
        this.renderCenteredString(1, "Equipment");
    }
}
