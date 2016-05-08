package rznw.ui.instructions;

public class StatsInstructionsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Stats";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "When you level up, you may allocate points to upgrade your stats.",
            "",
            "Stats are common to all four character classes and work in a passive fashion.",
            "",
            "For more information about your stats, visit the stats menu."
        };
    }
}
