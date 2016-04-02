package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;

public class ViperDagger extends Weapon
{
    public String getDisplayName()
    {
        return "Viper Dagger";
    }

    public int getDamage()
    {
        return 8;
    }

    public int getValue()
    {
        return 400;
    }

    public void damagedEnemyCharacter(EnemyCharacter enemyCharacter)
    {
        System.out.println("Enemy is poisoned by the viper dagger");
        enemyCharacter.getStatusEffects().poison();
    }
}
