package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

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
        enemyCharacter.getStatusEffects().poison();

        LogRendererFactory.instance().log("Enemy poisoned.");
    }

    public int getEquipmentNumber()
    {
        return ViperDagger.EQUIPMENT_NUMBER;
    }
}
