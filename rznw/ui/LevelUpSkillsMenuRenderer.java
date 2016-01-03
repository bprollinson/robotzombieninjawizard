package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.skill.PassiveSkill;
import rznw.game.skill.Skill;
import rznw.game.skill.SkillFactory;

public class LevelUpSkillsMenuRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 6;
    private static final int MENU_ROW_HEIGHT = 1;

    public LevelUpSkillsMenuRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, int numPoints, MainCharacter mainCharacter, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            SkillFactory factory = mainCharacter.getSkillFactory();
            Skill skill = factory.getSkill(state.getEntryNumber());
            String skillType = "";
            if (skill instanceof PassiveSkill)
            {
                skillType = " (Passive)";
            }
            else if (skill != null)
            {
                skillType = " (Active)";
            }

            this.renderCenteredString(1, mainCharacter.getSkillName(state.getEntryNumber()) + skillType);

            int row = 3;

            row += this.renderStringWithNewlines(3, mainCharacter.getSkillDescription(state.getEntryNumber()));
            row++;

            int skillPoints = mainCharacter.getSkillPoints(state.getEntryNumber());

            if (skill != null)
            {
                if (skillPoints > 0)
                {
                    this.frame.renderDisplayString(row, 0, "Current level:");
                    row++;

                    String[] currentLevelStats = skill.getStats(mainCharacter, skillPoints);
                    for (int i = 0; i < currentLevelStats.length; i++)
                    {
                        this.frame.renderDisplayString(row + i, 0, currentLevelStats[i]);
                    }

                    row += currentLevelStats.length + 1;
                }

                this.frame.renderDisplayString(row, 0, "Next level:");
                row++;

                String[] nextLevelStats = skill.getStats(mainCharacter, skillPoints + 1);
                for (int i = 0; i < nextLevelStats.length; i++)
                {
                    this.frame.renderDisplayString(row + i, 0, nextLevelStats[i]);
                }
            }

            this.renderCenteredString(30, "Press 'i' to return to the skill menu");
        }
        else
        {
            this.renderCenteredString(1, "Level Up!");
            this.renderCenteredString(3, "Skill points remaining: " + numPoints);

            this.renderPointGroup(mainCharacter, 0, 5);
            this.renderPointGroup(mainCharacter, 1, 11);
            this.renderPointGroup(mainCharacter, 2, 17);
            this.renderPointGroup(mainCharacter, 3, 23);

            this.renderCenteredString(30, "Press 'i' for skill information");

            this.renderCursor(state);
        }
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

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = LevelUpSkillsMenuRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * LevelUpSkillsMenuRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}
