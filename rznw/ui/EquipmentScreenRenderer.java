package rznw.ui;

public class EquipmentScreenRenderer extends MenuScreenRenderer
{
    public EquipmentScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Equipment");

        this.renderCenteredString(4, "Weapons");
        this.renderCenteredString(6, "Shields");

        this.renderCursor(state);
    }

    private void renderCursor(MenuState state)
    {
        int row = 4 + 2 * state.getEntryNumber();

        this.frame.renderDisplayCharacter(row, 14, 'X');
    }
}
