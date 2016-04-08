package rznw.turn;

import rznw.game.Character;
import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Shield;
import rznw.map.Map;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MainCharacterMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.RockWall;
import rznw.map.GameWorld;
import rznw.turn.positionchange.PositionChange;
import rznw.utility.RandomNumberGenerator;

public class CollisionHandler
{
    public boolean handleCollision(Character character, Map map, PositionChange positionChange, GameWorld gameWorld)
    {
        int newRow = positionChange.getFinalRow();
        int newColumn = positionChange.getFinalColumn();

        if (newRow < 0 || newRow >= Map.NUM_ROWS || newColumn < 0 || newColumn >= Map.NUM_COLUMNS)
        {
            return true;
        }

        MapElement collisionTest = map.getElement(newRow, newColumn);
        if (collisionTest == null)
        {
            return false;
        }

        if (!this.elementsHaveInteraction(character, collisionTest))
        {
            return true;
        }

        if (collisionTest instanceof RockWall)
        {
            ((RockWall)collisionTest).damage(character.getDamage());
            if (((RockWall)collisionTest).getHP() <= 0)
            {
                map.setElement(collisionTest.getRow(), collisionTest.getColumn(), null);
            }

            return true;
        }

        this.handleCharacterInteraction(character, (CharacterMapElement)collisionTest, map, gameWorld);

        return true;
    }

    private boolean elementsHaveInteraction(Character character, MapElement collisionTest)
    {
        if (collisionTest instanceof RockWall)
        {
            return true;
        }

        if (!(collisionTest instanceof CharacterMapElement))
        {
            return false;
        }

        if (collisionTest instanceof MainCharacterMapElement && character instanceof SummonedCharacter)
        {
            return false;
        }

        if (collisionTest instanceof EnemyMapElement && character instanceof EnemyCharacter)
        {
            return false;
        }

        return true;
    }

    private void handleCharacterInteraction(Character character, CharacterMapElement collisionTest, Map map, GameWorld gameWorld)
    {
        if (!character.meleeAttackHits())
        {
            if (character instanceof MainCharacter)
            {
                System.out.println("Main character melee miss!");
            }
            else
            {
                System.out.println("Enemy character melee miss!");
            }

            return;
        }

        Character otherCharacter = ((CharacterMapElement)collisionTest).getCharacter();
        if (otherCharacter.dodgesAttack())
        {
            if (otherCharacter instanceof MainCharacter)
            {
                System.out.println("Main character dodge!");

                Shield shield = ((MainCharacter)otherCharacter).getEquipment().getEquippedShield();
                if (shield != null)
                {
                    shield.dodgesAttack((MainCharacter)otherCharacter);
                }
            }
            else
            {
                System.out.println("Enemy character dodge!");
            }

            return;
        }

        if (character instanceof MainCharacter)
        {
            System.out.println("Main character melee hit!");
        }
        else
        {
            System.out.println("Enemy character melee hit!");
        }

        if (otherCharacter instanceof SummonedCharacter)
        {
            System.out.println("Summoned character HP before: " + otherCharacter.getHP());
        }

        if (otherCharacter instanceof EnemyCharacter && character instanceof SummonedCharacter)
        {
            System.out.println("Summon is hitting an enemy, HP before: " + otherCharacter.getHP());
        }

        int damage = otherCharacter.damage(character.getDamage(), character, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);

        if (otherCharacter instanceof EnemyCharacter && character instanceof SummonedCharacter)
        {
            System.out.println("Summon is hitting an enemy, HP after: " + otherCharacter.getHP());
        }

        if (otherCharacter instanceof SummonedCharacter)
        {
            System.out.println("Summoned character HP after: " + otherCharacter.getHP());
        }

        if (character.getStatusEffects().signalWeaponEnabled())
        {
            System.out.println("Checking signal weapon");

            int confuseProbability = 5 * ((MainCharacter)character).getSpellPoints(14);
            System.out.println("Confuse probability: " + confuseProbability);

            if (RandomNumberGenerator.rollSucceeds(confuseProbability))
            {
                System.out.println("Confuse successful!");
                otherCharacter.getStatusEffects().confuse();
            }
        }

        if (character instanceof EnemyCharacter)
        {
            ((EnemyCharacter)character).damagedMainCharacter((MainCharacter)otherCharacter, damage);
        }
        else
        {
            ((MainCharacter)character).damagedEnemyCharacter((EnemyCharacter)otherCharacter, damage);
        }
    }
}
