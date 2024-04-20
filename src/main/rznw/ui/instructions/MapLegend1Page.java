package rznw.ui.instructions;

public class MapLegend1Page extends InstructionsPage
{
    public String getPageTitle()
    {
        return "Map Legend 1";
    }

    public String[] getPageContent()
    {
        return new String[] {
            "R: Robot",
            "Z: Zombie",
            "N: Ninja",
            "W: Wizard",
            "",
            "#: Wall",
            "*: Passageway",
            "V: Stairs",
            "&: Waypoint",
            "-: Sprung Trap",
            "_: Visible Trap",
            ";: Poison Blotch",
            "F: Fire",
            "=: Rock Wall",
            "S: Summoned Zombie",
            "G: Golem",
            "",
            "a: Assassin",
            "b: Beast Summoner",
            "c: Crusher",
            "d: Dragon",
            "e: Enchanter",
            "f: Fume Beast",
            "g: Gravity Wizard"
        };
    }
}
