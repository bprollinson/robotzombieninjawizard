package rznw.game.maincharacter;

import rznw.game.spell.SpellFactory;
import rznw.game.spell.ninja.NinjaSpellFactory;
import rznw.map.element.MainCharacterMapElement;

public class Ninja extends MainCharacter
{
    private static char mapCharacter = 'N';
    private static String[] spellCategory = {
        "Stealth Attack",
        "Projectile",
        "Thievery",
        "Counterattack"
    };

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Ninja.mapCharacter, this);
    }

    public String getCharacterClass()
    {
        return "Ninja";
    }

    public int getCharacterClassNumber()
    {
        return MainCharacterGenerator.CLASS_NINJA;
    }

    public String getSpellCategory(int categoryNumber)
    {
        return Ninja.spellCategory[categoryNumber];
    }

    public SpellFactory getSpellFactory()
    {
        return new NinjaSpellFactory();
    }
}
