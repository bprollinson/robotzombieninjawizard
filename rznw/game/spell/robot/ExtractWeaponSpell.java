package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.BloodSword;
import rznw.game.maincharacter.inventory.CrusherHammer;
import rznw.game.maincharacter.inventory.DeathScythe;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.GauntletOfDarkness;
import rznw.game.maincharacter.inventory.GravityBlade;
import rznw.game.maincharacter.inventory.IceRod;
import rznw.game.maincharacter.inventory.InvisibilityWand;
import rznw.game.maincharacter.inventory.MagicJavelin;
import rznw.game.maincharacter.inventory.QuicksandHammer;
import rznw.game.maincharacter.inventory.RiddleWand;
import rznw.game.maincharacter.inventory.ThiefGlove;
import rznw.game.maincharacter.inventory.ViperDagger;
import rznw.game.maincharacter.inventory.WandOfSummoning;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.maincharacter.inventory.WoodenSword;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.utility.RandomNumberGenerator;

public class ExtractWeaponSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Extract Weapon";
    }

    public String getDescription()
    {
        return "Damages a nearby enemy. Your character will possibly steal a weapon from that enemy.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Extract Weapon");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();
        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(characterElement.getRow(), characterElement.getColumn(), direction);

        Map map = gameWorld.getMap();
        MapElement collisionElement = map.getElement(positionChange.getFinalRow(), positionChange.getFinalColumn());

        if (collisionElement != null && collisionElement.isEnemy())
        {
            System.out.println("Is an enemy");

            int damage = 20 + 10 * spellPoints;

            EnemyCharacter enemy = (EnemyCharacter)((EnemyMapElement)collisionElement).getCharacter();
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);

            int chanceToSteal = 5 * spellPoints;

            if (RandomNumberGenerator.rollSucceeds(chanceToSteal))
            {
                System.out.println("Roll succeeds");
                Weapon weapon = this.getWeapon(enemy);

                if (weapon != null)
                {
                    try
                    {
                        character.getEquipment().addEquipment(new EquipmentGroup(weapon, 1));
                    }
                    catch (EquipmentFullException efe)
                    {
                        System.out.println("Equipment full");
                    }
                }
            }
            else
            {
                System.out.println("Roll fails");
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (20 + 10 * spellPoints),
            "Chance to steal: " + 5 * spellPoints + "%"
        };
    }

    private Weapon getWeapon(EnemyCharacter enemy)
    {
        EquipmentItem drop = enemy.getEquipmentDrop();

        if (drop == null)
        {
            System.out.println("Extracting enemy's weapon: " + drop);
            return null;
        }

        if (drop.isWeapon())
        {
            return (Weapon)drop;
        }

        return this.getRandomWeapon();
    }

    private Weapon getRandomWeapon()
    {
        System.out.println("Extracting enemy weapon");
        int index = RandomNumberGenerator.randomInteger(0, 13);

        switch (index)
        {
            case 0:
                return new BloodSword();
            case 1:
                return new CrusherHammer();
            case 2:
                return new DeathScythe();
            case 3:
                return new GravityBlade();
            case 4:
                return new GauntletOfDarkness();
            case 5:
                return new IceRod();
            case 6:
                return new InvisibilityWand();
            case 7:
                return new MagicJavelin();
            case 8:
                return new QuicksandHammer();
            case 9:
                return new RiddleWand();
            case 10:
                return new ThiefGlove();
            case 11:
                return new ViperDagger();
            case 12:
                return new WandOfSummoning();
            case 13:
                return new WoodenSword();
            default:
                return null;
        }
    }
}
