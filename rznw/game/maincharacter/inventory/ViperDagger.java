package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class ViperDagger extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 12;

    public String getDisplayName()
    {
        return "Viper Dagger";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Poisons enemies",
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
        System.out.println("Enemy is poisoned by the viper dagger");
        enemyCharacter.getStatusEffects().poison();
    }

    public int getEquipmentNumber()
    {
        return ViperDagger.EQUIPMENT_NUMBER;
    }
}
