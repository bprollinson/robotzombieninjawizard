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
    private static String[] spellName = {
        "Rock Wall",
        "Meteor Shower",
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
    private static String[] spellDescription = {
        "Generates a barrier on any side of you, protecting you from enemeny attack. The length, distance and duration of the barrier depend on your spell level. The wall can only be damaged by melee attack but cannot dodge attacks.",
        "Meteors rain down from the sky, crushing your enemies. The radius and concentration of the meteors varies with your spell level.",
        "The earth trembles, damaging all enemies within a radius of you. This radius increases with your spell level.",
        "Summons a golem to fight alongside you. This golem will wander around independently, continuing to battle until its HP are exhausted. Only one golem may be summoned at any time.",
        "Creates a ring of fire three spaces away from you. The damage and duration increase as your spell level increases.",
        "Shoot a projectile in any direction. When this projectile hits an enemy or object, it explodes, damaging all enemies close to the blast area. The blast radius increases with your spell level.",
        "Travels through enemies, damaging them all until a wall is encountered.",
        "Shoots a projectile that chains between enemies. Travels in a straight line, then jumps between enemies within a radius of eachother. Attacks each enemy at most once.",
        "Pushes nearby enemies away. The distance and radius of effect increase with your spell level.",
        "Allows you to escape your surroundings, moving you to a safer location. As your spell level increases, the probability of arriving in a safe location increases.",
        "Shoots a projectile in the chosen direction. An enemy hit by this projectile will be shot back, hitting and damaging other enemies in a chain reaction.",
        "Allows you to travel back up to the previous dungeon level. This regenerates a random level of difficulty one less than the current level. Can't be used on the first dungeon level.",
        "Damages all enemies within a radius of you, with a chance to freeze each one. Radius and chance to freeze increase as spell level increases.",
        "Replenishes lost HP. Healing increases as spell level increases.",
        "Has a chance to heal all status effects currently affecting you. MP cost decreases and chance to succeed increases as spell level increases.",
        "Shoots a projectile in the chosen direction. This projectile deals damage to an opponent equal to a percentage of that opponent's remaining HP. The percentage increases as your spell level increases."
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

    public String getSpellDescription(int spellNumber)
    {
        return Wizard.spellDescription[spellNumber];
    }

    public SpellFactory getSpellFactory()
    {
        return new WizardSpellFactory();
    }
}
