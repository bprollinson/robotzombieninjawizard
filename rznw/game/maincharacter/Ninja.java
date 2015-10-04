package rznw.game.maincharacter;

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
    private static String[] spellName = {
        "Stun Strike",
        "Roundhouse Strike",
        "Poison Strike",
        "Armor Break",
        "Pin Strike",
        "Shuriken Star",
        "Smoke Cluster",
        "Stun Bomb",
        "Steal Gold",
        "Steal Item",
        "Steal Equipment",
        "Steal Experience",
        "Smoke Bomb",
        "Counterstrike",
        "Reverse Pain",
        "Death Strike"
    };

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Ninja.mapCharacter, this);
    }

    public String getCharacterClass()
    {
        return "Ninja";
    }

    public String getSpellCategory(int categoryNumber)
    {
        return Ninja.spellCategory[categoryNumber];
    }

    public String getSpellName(int spellNumber)
    {
        return Ninja.spellName[spellNumber];
    }
}
