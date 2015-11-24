package rznw.turn;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.RockWall;
import rznw.turn.positionchange.PositionChange;

public class CollisionHandler
{
    public boolean handleCollision(Character character, Map map, PositionChange positionChange)
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

        this.handleCharacterInteraction(character, (CharacterMapElement)collisionTest, map);

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

        if (collisionTest instanceof EnemyMapElement && character instanceof EnemyCharacter)
        {
            return false;
        }

        return true;
    }

    private void handleCharacterInteraction(Character character, CharacterMapElement collisionTest, Map map)
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

        otherCharacter.damage(character.getDamage(), character);
    }
}
