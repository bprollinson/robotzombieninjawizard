package rznw.game.maincharacter;

import rznw.map.element.MainCharacterMapElement;

public class Wizard extends MainCharacter
{
    private static char mapCharacter = 'W';

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Wizard.mapCharacter, this);
    }

    public String getCharacterClass()
    {
        return "Wizard";
    }
}
