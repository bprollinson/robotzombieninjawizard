package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

public class SpellsScreenRenderer extends MenuScreenRenderer
{
    public SpellsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter mainCharacter)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Spells");

        this.renderPointGroup(mainCharacter, 0, 3);
        this.renderPointGroup(mainCharacter, 1, 9);
        this.renderPointGroup(mainCharacter, 2, 15);
        this.renderPointGroup(mainCharacter, 3, 21);
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        String groupDisplay = mainCharacter.getSpellCategory(groupNumber);
        this.frame.renderDisplayString(startRow, 2, groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int groupPositionDisplay = i + 1;
            int pointIndex = groupNumber * 4 + i;
            this.frame.renderDisplayString(startRow + i + 1, 2, mainCharacter.getSpellName(pointIndex) + ": " + mainCharacter.getSpellPoints(pointIndex));
        }
    }
}
