package rznw.ui.instructions;

public class SkillsInstructionsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Skill";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "When you level up, you may allocate points to learn and upgrade your skills.",
            "",
            "Stats are common to all four character classes. They may work in an active or passive fashion, depending on the skill.",
            "",
            "For more information about your skills, visit the skills menu."
        };
    }
}
