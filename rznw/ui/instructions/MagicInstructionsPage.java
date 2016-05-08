package rznw.ui.instructions;

public class MagicInstructionsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Magic";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "When you level up, you may allocate points to learn and upgrade your spells.",
            "",
            "Casting spells requires you to spend MP (magic points). As you upgrade your spells, you will reduce their MP cost and increase their effectiveness.",
            "",
            "Some spells are directed, and can be cast in any of four directions (up, down, left or right). In this case, an on-screen prompt will be displayed.",
            "",
            "Spells are specific to your character class. Try playing with each of the four classes to see which you like the best.",
            "",
            "For more information about your spells, visit the spells menu."
        };
    }
}
