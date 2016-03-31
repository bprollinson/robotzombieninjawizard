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
    private static String[] spellDescription = {
        "Powers down your systems to regenerate lost health.",
        "Causes widespread damage over an area on the map in any of the four directions from you.",
        "You explode, dealing damage to yourself and nearby enemies.",
        "Steals MP from nearby enemies.",
        "Allows you to zip across the map in a straight line.",
        "Allows you to zip across the map, jumping over obstacles in your path.",
        "Shoots an exploding projectile at your enemies, also causing damage to enemies around the blast area. The rocket travels in a straight line.",
        "Damages an enemy and has a chance to stun them. The blast travels in a straight line.",
        "Deals damage to the nearest enemy, and all on the current dungeon level of the same type. The nearest enemies is targeted automatically regardless of position.",
        "Levels down all enemies within a radius of you. This decreases their power, but also decreases the rewards achieved for defeating them.",
        "Increases the drop rate of enemies within a radius of you. This increases both the amount of gold and the frequency of items and equipment dropped.",
        "Levels up all enemies within a radius of you. This increases the rewards achieved for defeating them, but also increases their power.",
        "Damages a nearby enemy. Your character will possibly steal an item from that enemy.",
        "Damages a nearby enemy. If this attack kills that enemy, your defense is temporarily increased.",
        "Temporarily grants you the possibility of confusing enemies during melee combat.",
        "Temporarily increases the power level of weapons and armor dropped by enemies."
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

    public String getSpellDescription(int spellNumber)
    {
        return Robot.spellDescription[spellNumber];
    }

    public SpellFactory getSpellFactory()
    {
        return new RobotSpellFactory();
    }
}
