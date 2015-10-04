package rznw.ui;

import rznw.game.maincharacter.MainCharacter;

public class SkillsScreenRenderer extends MenuScreenRenderer
{
    public SkillsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MainCharacter mainCharacter)
    {
        this.clearScreen();
        this.renderCenteredString(1, "Skills");

        this.renderPointGroup(mainCharacter, 0, 3);
        this.renderPointGroup(mainCharacter, 1, 9);
        this.renderPointGroup(mainCharacter, 2, 15);
        this.renderPointGroup(mainCharacter, 3, 21);
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        String groupDisplay = MainCharacter.getSkillCategory(groupNumber);
        this.frame.renderDisplayString(startRow, 2, groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int pointIndex = groupNumber * 4 + i;
            this.frame.renderDisplayString(startRow + i + 1, 2, MainCharacter.getSkillName(pointIndex) + ": " + mainCharacter.getSkillPoints(pointIndex));
        }
    }
}
