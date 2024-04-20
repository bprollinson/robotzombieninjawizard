package rznw.ui;

import rznw.ui.instructions.InstructionsFactory;
import rznw.ui.instructions.InstructionsPage;

public class InstructionsScreenRenderer extends MenuScreenRenderer
{
    public InstructionsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state)
    {
        this.clearScreen();

        InstructionsPage instructions = InstructionsFactory.getPage(state.getEntryNumber());

        this.renderCenteredString(1, "Instructions - " + instructions.getPageTitle());

        String[] pageContent = instructions.getPageContent();
        int row = 3;

        for (int i = 0; i < pageContent.length; i++)
        {
            row += this.renderStringWithNewlines(row, pageContent[i]);
        }

        this.frame.renderDisplayString(28, 0, "Page " + (state.getEntryNumber() + 1) + " / " + InstructionsFactory.NUM_PAGES);

        if (state.getEntryNumber() > 0)
        {
            this.frame.renderDisplayString(30, 0, "Up for more");
        }

        if (state.getEntryNumber() < InstructionsFactory.NUM_PAGES - 1)
        {
            this.frame.renderDisplayString(31, 0, "Down for more");
        }
    }
}
