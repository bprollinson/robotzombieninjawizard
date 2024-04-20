package rznw.game.maincharacter;

import rznw.game.spell.SpellFactory;
import rznw.game.spell.zombie.ZombieSpellFactory;
import rznw.map.element.MainCharacterMapElement;

public class Zombie extends MainCharacter
{
    private static char mapCharacter = 'Z';
    private static String[] spellCategory = {
        "Thick Skin",
        "Feeding",
        "Infection",
        "Zombie Pack"
    };

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Zombie.mapCharacter, this);
    }

    public String getCharacterClass()
    {
        return "Zombie";
    }

    public int getCharacterClassNumber()
    {
        return MainCharacterGenerator.CLASS_ZOMBIE;
    }

    public String getSpellCategory(int categoryNumber)
    {
        return Zombie.spellCategory[categoryNumber];
    }

    public SpellFactory getSpellFactory()
    {
        return new ZombieSpellFactory();
    }
}
