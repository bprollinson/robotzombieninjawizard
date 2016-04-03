package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;

public class RiddleWand extends Weapon
{
    public String getDisplayName()
    {
        return "Riddle Wand";
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
        System.out.println("Enemy is confused by the riddle wand");
        enemyCharacter.getStatusEffects().confuse();
    }
}
