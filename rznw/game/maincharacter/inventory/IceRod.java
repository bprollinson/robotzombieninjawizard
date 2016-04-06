package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.utility.RandomNumberGenerator;

public class IceRod extends Weapon
{
    public String getDisplayName()
    {
        return "Ice Rod";
    }

    public int getDamage()
    {
        return 8;
    }

    public int getValue()
    {
        return 400;
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage)
    {
        System.out.println("Enemy is frozen by the ice rod");

        if (RandomNumberGenerator.rollSucceeds(50))
        {
            enemyCharacter.getStatusEffects().freeze();
        }
    }
}
