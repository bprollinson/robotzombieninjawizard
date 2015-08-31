package rznw.turn;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.PositionChange;

public class CollisionHandler
{
    public boolean handleCollision(Character character, Map map, PositionChange positionChange)
    {
        int newRow = positionChange.getFinalRow();
        int newColumn = positionChange.getFinalColumn();

        MapElement collisionTest = map.getElement(newRow, newColumn);
        if (collisionTest == null)
        {
            return false;
        }

        if (collisionTest instanceof EnemyMapElement && character instanceof MainCharacter)
        {
            EnemyCharacter enemy = ((EnemyMapElement)collisionTest).getEnemyCharacter();
            enemy.damage(character.getDamage());

            if (enemy.isDead())
            {
                map.setElement(newRow, newColumn, null);
            }
        }

        return true;
    }
}
