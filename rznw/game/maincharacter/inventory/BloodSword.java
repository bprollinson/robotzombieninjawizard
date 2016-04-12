package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class BloodSword extends Weapon
{
    public String getDisplayName()
    {
        return "Blood Sword";
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
        System.out.println("Healing with the blood sword");

        int healHP = (int)Math.floor(0.1 * damage);
        System.out.println("Healing " + healHP + " from " + damage + " damage");

        mainCharacter.heal(healHP);
    }
}
