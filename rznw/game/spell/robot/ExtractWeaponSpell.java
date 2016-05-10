package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
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
            return null;
        }

        if (drop.isWeapon())
        {
            return (Weapon)drop;
        }

        return new WoodenSword();
    }
}
