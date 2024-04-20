package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class ThiefGlove extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 11;

    public String getDisplayName()
    {
        return "Thief Glove";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Steals gold from enemies",
            "Gold stolen: 10",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 8;
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        mainCharacter.getInventory().addGold(10);

        LogRendererFactory.instance().log("Stole 10 gold from enemy.");
    }

    public int getEquipmentNumber()
    {
        return ThiefGlove.EQUIPMENT_NUMBER;
    }
}
