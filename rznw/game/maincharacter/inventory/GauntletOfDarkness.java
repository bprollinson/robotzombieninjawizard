package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class GauntletOfDarkness extends Weapon
{
    public String getDisplayName()
    {
        return "Gauntlet of Darkness";
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
        System.out.println("Enemy is hit by the gauntlet of darkness");

        if (enemyCharacter.getHP() > 1 && RandomNumberGenerator.rollSucceeds(25))
        {
            System.out.println("Gauntlet success");
            enemyCharacter.setHP(1);
        }
    }
}
