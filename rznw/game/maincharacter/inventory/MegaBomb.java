package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

import java.util.Collection;
import java.util.Iterator;

public class MegaBomb extends InventoryItem
{
    public static final int ITEM_NUMBER = 6;
    private static int RADIUS = 4;
    private static int DAMAGE = 300;

    public String getDisplayName()
    {
        return "Mega Bomb";
    }

    public String getDescription()
    {
        return "Damages nearby enemies badly.";
    }

    public void useOnCharacter(MainCharacter character, GameWorld gameWorld)
    {
        Map map = gameWorld.getMap();
        MapElement mainCharacterElement = character.getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(mainCharacterElement.getRow() - MegaBomb.RADIUS, mainCharacterElement.getColumn() - MegaBomb.RADIUS, mainCharacterElement.getRow() + MegaBomb.RADIUS, mainCharacterElement.getColumn() + MegaBomb.RADIUS);

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            System.out.println("Damaging an enemy with a bomb");
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            enemy.damage(MegaBomb.DAMAGE, character, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);
        }
    }

    public int getValue()
    {
        return 200;
    }

    public int getItemNumber()
    {
        return MegaBomb.ITEM_NUMBER;
    }
}
