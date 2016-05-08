package rznw.ui.instructions;

public class CombatInstructionsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Combat";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "When standing adjacent to an enemy, moving in that enemy's direction will cause you to attack them in physical combat.",
            "",
            "After your attack, enemies may respond and counterattack.",
            "",
            "Your chance to damage an enemy and the damage you deal depend on your stats as well as enemy stats."
        };
    }
}
