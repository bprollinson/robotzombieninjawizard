package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class IceRod extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 6;

    public String getDisplayName()
    {
        return "Ice Rod";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "",
            "Freezes enemies",
            "Chance to freeze: 50%",
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
        if (RandomNumberGenerator.rollSucceeds(50))
        {
            enemyCharacter.getStatusEffects().freeze();

            LogRendererFactory.instance().log("Enemy frozen.");
        }
    }

    public int getEquipmentNumber()
    {
        return IceRod.EQUIPMENT_NUMBER;
    }
}
