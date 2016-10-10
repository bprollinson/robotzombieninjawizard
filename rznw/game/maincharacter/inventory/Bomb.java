package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

import java.util.Collection;
import java.util.Iterator;

public class Bomb extends InventoryItem
{
    public static final int ITEM_NUMBER = 1;
    private static int RADIUS = 2;
    private static int DAMAGE = 100;

    public String getDisplayName()
    {
        return "Bomb";
    }

    public String getDescription()
    {
        return "Damages nearby enemies.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        Map map = gameWorld.getMap();
        MapElement mainCharacterElement = character.getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(mainCharacterElement.getRow() - Bomb.RADIUS, mainCharacterElement.getColumn() - Bomb.RADIUS, mainCharacterElement.getRow() + Bomb.RADIUS, mainCharacterElement.getColumn() + Bomb.RADIUS);

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            System.out.println("Damaging an enemy with a bomb");
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            enemy.damage(Bomb.DAMAGE, character, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);
        }
    }

    public int getValue()
    {
        return 50;
    }

    public int getItemNumber()
    {
        return Bomb.ITEM_NUMBER;
    }
}
