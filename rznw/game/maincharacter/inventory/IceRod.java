package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class IceRod extends Weapon
{
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

    public int getValue()
    {
        return 400;
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Enemy is frozen by the ice rod");

        if (RandomNumberGenerator.rollSucceeds(50))
        {
            enemyCharacter.getStatusEffects().freeze();
        }
    }
}
