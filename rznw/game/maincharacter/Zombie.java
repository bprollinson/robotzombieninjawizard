package rznw.game.maincharacter;

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
    private static String[] spellName = {
        "Poison Skin",
        "Resist Damage",
        "Thorn Skin",
        "Barbed Skin",
        "Feed Flesh",
        "Feed Brain",
        "Feed Past",
        "Feed Mind",
        "Locust Swarm",
        "Poison Cloud",
        "Infectious Rage",
        "Blotch",
        "Summon Zombie",
        "Infer Zombie",
        "Multiply Zombies",
        "Explode Zombies"
    };

    public void generateMapElement(int row, int column)
    {
        this.mapElement = new MainCharacterMapElement(row, column, Zombie.mapCharacter, this);
    }

    public String getCharacterClass()
    {
        return "Zombie";
    }

    public String getSpellCategory(int categoryNumber)
    {
        return Zombie.spellCategory[categoryNumber];
    }

    public String getSpellName(int spellNumber)
    {
        return Zombie.spellName[spellNumber];
    }
}
