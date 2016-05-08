package rznw.ui.instructions;

public class IntroInstructionsPage extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Introduction";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "Welcome to Robot Zombie Ninja Wizard, a dungeon crawling adventure.",
            "",
            "In this game, you will take on the role of one of four characters to traverse the deadly dungeon and defeat the evil wizard Zenith before it's too late.",
            "",
            "Many challenges await you, including fearsome foes and perilous traps.",
            "",
            "Use your spells, equipment and cunning to clear your path to victory and destroy evil for good!"
        };
    }
}
