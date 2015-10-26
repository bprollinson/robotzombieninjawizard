package rznw.turn;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.PositionChange;

public class CollisionHandler
{
    private KillBonusGranter killBonusGranter;

    public CollisionHandler()
    {
        this.killBonusGranter = new KillBonusGranter();
    }

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

        if (!character.meleeAttackHits())
        {
            if (character instanceof MainCharacter)
            {
                System.out.println("Melee miss!");
            }

            return true;
        }

        if (character instanceof MainCharacter)
        {
            System.out.println("Melee hit!");
        }

        Character otherCharacter = ((CharacterMapElement)collisionTest).getCharacter();
        otherCharacter.damage(character.getDamage());
        if (otherCharacter.isDead())
        {
            this.killBonusGranter.grantKillBonuses(character, otherCharacter);
            map.setElement(newRow, newColumn, null);
        }

        return true;
    }

    private boolean elementsHaveInteraction(Character character, MapElement collisionTest)
    {
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
}
