package rznw.ui;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.MainCharacterSkills;
import rznw.game.skill.PassiveSkill;
import rznw.game.skill.Skill;
import rznw.game.skill.SkillFactory;

public class SkillsScreenRenderer extends MenuScreenRenderer
{
    private static final int MENU_ENTRY_FIRST_ROW = 4;
    private static final int MENU_ROW_HEIGHT = 1;

    public SkillsScreenRenderer(MainGameFrame frame)
    {
        super(frame);
    }

    public void render(MenuState state, MainCharacter mainCharacter, boolean showingDescription)
    {
        this.clearScreen();

        if (showingDescription)
        {
            SkillFactory factory = mainCharacter.getSkills().getSkillFactory();
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

            this.renderCenteredString(1, mainCharacter.getSkills().getSkillName(state.getEntryNumber()) + skillType);

            int row = 3;

            row += this.renderStringWithNewlines(3, mainCharacter.getSkills().getSkillDescription(state.getEntryNumber()));
            row++;

            int skillPoints = mainCharacter.getSkills().getSkillPoints(state.getEntryNumber());

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
            this.renderCenteredString(1, "Skills");

            this.renderPointGroup(mainCharacter, 0, 3);
            this.renderPointGroup(mainCharacter, 1, 9);
            this.renderPointGroup(mainCharacter, 2, 15);
            this.renderPointGroup(mainCharacter, 3, 21);

            this.renderCenteredString(30, "Press 'i' for skill information");

            this.renderCursor(state);
        }
    }

    private void renderPointGroup(MainCharacter mainCharacter, int groupNumber, int startRow)
    {
        String groupDisplay = MainCharacterSkills.getSkillCategory(groupNumber);
        this.frame.renderDisplayString(startRow, 2, groupDisplay);

        for (int i = 0; i < 4; i++)
        {
            int pointIndex = groupNumber * 4 + i;
            this.frame.renderDisplayString(startRow + i + 1, 2, MainCharacterSkills.getSkillName(pointIndex) + ": " + mainCharacter.getSkills().getSkillPoints(pointIndex));
        }
    }

    private void renderCursor(MenuState state)
    {
        int rowSpaces = 2 * (state.getEntryNumber() / 4);
        int row = SkillsScreenRenderer.MENU_ENTRY_FIRST_ROW + state.getEntryNumber() * SkillsScreenRenderer.MENU_ROW_HEIGHT + rowSpaces;

        this.frame.renderDisplayCharacter(row, 0, 'X');
    }
}
