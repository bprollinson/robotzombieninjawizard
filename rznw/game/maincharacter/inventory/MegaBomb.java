package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;

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
        LogRendererFactory.instance().log("Using mega bomb.");

        Map map = gameWorld.getMap();
        MapElement mainCharacterElement = character.getMapElement();
        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(mainCharacterElement.getRow() - MegaBomb.RADIUS, mainCharacterElement.getColumn() - MegaBomb.RADIUS, mainCharacterElement.getRow() + MegaBomb.RADIUS, mainCharacterElement.getColumn() + MegaBomb.RADIUS);

        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            int damage = enemy.damage(MegaBomb.DAMAGE, character, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);
            LogRendererFactory.instance().log("Dealt " + damage + " damage to " + enemy.getLogName() + ".");
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
