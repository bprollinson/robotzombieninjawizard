package rznw.ui.instructions;

public class MovementInstructionsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Movement";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "You can move around the dungeon using the arrow keys or the number pad.",
            "",
            "Your character can move horizontally, vertically, or diagonally, one space at a time.",
            "",
            "Pressing the center number pad key (5) allows you to skip your turn.",
            "",
            "Walls (#) are impassable, and separate rooms from the void that surrounds them.",
            "",
            "Use passageways (*) to traverse between rooms.",
            "",
            "In each level before the last, find the stairs down (V) and press Shift + V to travel down to the next level.",
            "",
            "However, beware! Once you exit a level, you may not return to it."
        };
    }
}
