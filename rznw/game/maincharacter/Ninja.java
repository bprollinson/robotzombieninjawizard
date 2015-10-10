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
    private static String[] spellDescription = {
        "You lunge in a straight line until an enemy is encountered. You damage that enemy, with a chance to stun it.",
        "You lunge in a straight line until an enemy is encountered. Then, you perform a roundhouse kick, damaging all enemies in spaces adjacent to you.",
        "You lunge in a straight line until an enemy is encountered. You damage that enemy, poisoning it.",
        "You lunge in a straight line until an enemy is encountered. You damage that enemy and decrease its defensive capability.",
        "Throws a projectile in a straight line. When this collides with an enemy, that enemy damaged and is pushed back.",
        "Throws a projectile in all eight directions.",
        "Throws a projectile in a straight line. When this collides with an enemy, it explodes, confusing all enemies within a radius of the impact.",
        "Throws a projectile in a straight line. When this collides with an enemy, it explodes, causing damage to all enemies within a radius of the impact, and has a chance to confuse those enemies.",
        "Has a chance to steal gold from an ememy within melee combat range.",
        "Has a chance to steal an item from an enemy within melee combat range.",
        "Has a chance to steal a piece of equipment from an enemy within melee combat range.",
        "Has a chance to steal experience from an enemy within melee combat range.",
        "The next time you are damaged by an enemy, you have a chance to escape to a safe location.",
        "The next time you are damaged by an enemy, you have a chance to auto-attack that enemy, dealing damage.",
        "Until your next turn, damage you would receive from enemies is converted into restored HP.",
        "The next time you are damaged by an enemy, you have a chance to auto-kill that enemy."
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

    public String getSpellDescription(int spellNumber)
    {
        return Ninja.spellDescription[spellNumber];
    }
}
