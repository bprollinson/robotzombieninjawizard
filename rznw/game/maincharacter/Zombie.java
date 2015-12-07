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
    private static String[] spellDescription = {
        "Enemies become poisoned when physically attacking you for a period of time. This does not affect ranged attacks.",
        "Reduce all damage you take for a period of time by increasing your defense. This affects all types of damage.",
        "Enemies take damage when physically attacking you for a period of time. This does not affect ranged attacks.",
        "Enemies have a chance to become stunned when physically attacking you for a period of time. This does not affect ranged attacks.",
        "Deals damage to an enemy and heals some of your HP. Has the same range as melee combat.",
        "Deals damage to an enemy and temporarily increases your magic power. Has the same range as melee combat.",
        "Deals damage to an enemy and grants you some experience. Has the same range as melee combat.",
        "Deals damage to an enemy and has a chance to confuse that enemy. Has the same range as melee combat.",
        "Shoots a projectile that chains between enemies. Travels in a straight line, then jumps between enemies within a radius of eachother. Attacks each enemy at most once.",
        "Poisons all enemies within an area. The area can be in any of the four directions from you.",
        "Increase all damage you deal for a period time. Using this spell will poison you.",
        "Leaves a poison spot on the ground. Enemies who come into contact with this spot will become poisoned.",
        "Summons a zombie that will independently battle enemies. This zombie will attack enemies in melee combat. It will continue to battle until its HP are exhausted.",
        "The next enemy killed by you or one of your zombies will turn into a zombie. This zombie will independently battle enemies in melee combat and stay within a radius of you. It will continue to battle until its HP are exhausted.",
        "Each zombie you control has a chance to multiply, creating an additional zombie.",
        "Each zombie you control explodes, dealing damage to all enemies within a radius of it."
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

    public String getSpellDescription(int spellNumber)
    {
        return Zombie.spellDescription[spellNumber];
    }

    public SpellFactory getSpellFactory()
    {
        return new ZombieSpellFactory();
    }
}
