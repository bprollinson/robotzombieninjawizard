package rznw.turn;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.map.Map;
import rznw.map.element.CharacterMapElement;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MainCharacterMapElement;
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

        if (!this.elementsHaveInteraction(character, collisionTest))
        {
            return true;
        }

        Character otherCharacter = ((CharacterMapElement)collisionTest).getCharacter();
        otherCharacter.damage(character.getDamage());
        if (otherCharacter.isDead())
        {
            this.grantKillBonuses(character, otherCharacter);
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

    private void grantKillBonuses(Character character, Character otherCharacter)
    {
        if (!(character instanceof MainCharacter) || !(otherCharacter instanceof EnemyCharacter))
        {
            return;
        }

        InventoryItem item = ((EnemyCharacter)otherCharacter).getItemDrop();
        if (item != null)
        {
            ((MainCharacter)character).getInventory().addItem(item);
        }
    }
}
