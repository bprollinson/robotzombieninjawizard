package rznw.game.maincharacter;

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
    private static String[] spellName = {
        "Rock Wall",
        "Metero Shower",
        "Earthquake",
        "Summon Golem",
        "Ring of Fire",
        "Fireball",
        "Heat Ray",
        "Arc Lightning",
        "Repel",
        "Teleport",
        "Ricochet Blast",
        "Updraft",
        "Ice Field",
        "Heal",
        "Cleanse",
        "Vital Zap"
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

    public String getSpellName(int spellNumber)
    {
        return Wizard.spellName[spellNumber];
    }
}
