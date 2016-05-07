package rznw.game.maincharacter;

import rznw.game.spell.SpellFactory;
import rznw.game.spell.robot.RobotSpellFactory;
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

    public SpellFactory getSpellFactory()
    {
        return new RobotSpellFactory();
    }
}
