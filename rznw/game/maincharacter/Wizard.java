package rznw.game.maincharacter;

import rznw.game.spell.SpellFactory;
import rznw.game.spell.wizard.WizardSpellFactory;
import rznw.map.element.MainCharacterMapElement;

public class Wizard extends MainCharacter
{
    private static char mapCharacter = 'W';
    private static String[] spellCategory = {
        "Earth",
        "Fire",
        "Air",
        "Water"
    };

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Wizard.mapCharacter, this);
    }

    public String getCharacterClass()
    {
        return "Wizard";
    }

    public String getSpellCategory(int categoryNumber)
    {
        return Wizard.spellCategory[categoryNumber];
    }

    public SpellFactory getSpellFactory()
    {
        return new WizardSpellFactory();
    }
}
