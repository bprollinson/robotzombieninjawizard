package rznw.ui.instructions;

public class StatusEffectsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Status Effects";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "In your adventures, you or your opponents may be affected by one or more status effects.",
            "",
            "Status effects may be induced by equipment or magic spells.",
            "",
            "The possible types of status effects are:",
            "",
            "Poison: A poisoned character takes additional damage each turn.",
            "Confusion: A confused character will move / fight in a random direction.",
            "Stun / Sleep / Freeze: A stunned character misses one or more subsequent turns.",
            "",
            "Status effects can be cured by items or spells."
        };
    }
}
