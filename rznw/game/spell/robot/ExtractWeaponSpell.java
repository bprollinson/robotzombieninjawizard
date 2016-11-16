package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.EquipmentItem;
import rznw.game.maincharacter.inventory.RandomWeaponGenerator;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.ui.LogRendererFactory;
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
        LogRendererFactory.instance().log("Casting extract weapon.");

        MainCharacter character = gameWorld.getMainCharacter();
        MapElement characterElement = character.getMapElement();
        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(characterElement.getRow(), characterElement.getColumn(), direction);

        Map map = gameWorld.getMap();
        MapElement collisionElement = map.getElement(positionChange.getFinalRow(), positionChange.getFinalColumn());

        if (collisionElement != null && collisionElement.isEnemy())
        {
            int damage = 20 + 10 * spellPoints;

            EnemyCharacter enemy = (EnemyCharacter)((EnemyMapElement)collisionElement).getCharacter();
            damage = enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");

            int chanceToSteal = 5 * spellPoints;

            if (RandomNumberGenerator.rollSucceeds(chanceToSteal))
            {
                Weapon weapon = this.getWeapon(enemy);

                if (weapon != null)
                {
                    try
                    {
                        character.getEquipment().addEquipment(new EquipmentGroup(weapon, 1));
                        LogRendererFactory.instance().log("Extracted " + weapon.getDisplayName().toLowerCase() + " from enemy.");
                    }
                    catch (EquipmentFullException efe)
                    {
                        LogRendererFactory.instance().log("Your equipment is full.");
                    }
                }
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

        if (drop.isWeapon())
        {
            return (Weapon)drop;
        }

        return new RandomWeaponGenerator().generateRandomWeapon();
    }
}
