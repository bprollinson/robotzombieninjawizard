package rznw.game.maincharacter;

import rznw.map.element.MainCharacterMapElement;

public class Robot extends MainCharacter
{
    private static char mapCharacter = 'R';
    private static String[] spellCategory = {
        "Power",
        "Rockets",
        "Genetics",
        "Armory"
    };
    private static String[] spellName = {
        "Power Down",
        "Electric Field",
        "Overload",
        "Suck Power",
        "Rocket Pack",
        "Rocket Jump",
        "Rocket Shot",
        "Paralyzing Blast",
        "Genetic Targeting",
        "Level Down",
        "Boost Genetics",
        "Level Up",
        "Extract Weapon",
        "Meat Shield",
        "Signal Weapon",
        "Power Search"
    };

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Robot.mapCharacter, this);
    }

    public String getCharacterClass()
    {
        return "Robot";
    }

    public String getSpellCategory(int categoryNumber)
    {
        return Robot.spellCategory[categoryNumber];
    }

    public String getSpellName(int spellNumber)
    {
        return Robot.spellName[spellNumber];
    }
}
