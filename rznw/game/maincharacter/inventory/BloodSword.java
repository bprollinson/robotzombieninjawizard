package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class BloodSword extends Weapon
{
    private static final int EQUIPMENT_NUMBER = 1;

    public String getDisplayName()
    {
        return "Blood Sword";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "",
            "Steals health from enemies",
            "Health stolen: 10% of damage",
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
        System.out.println("Healing with the blood sword");

        int healHP = (int)Math.floor(0.1 * damage);
        System.out.println("Healing " + healHP + " from " + damage + " damage");

        mainCharacter.heal(healHP);
    }

    public int getEquipmentNumber()
    {
        return BloodSword.EQUIPMENT_NUMBER;
    }
}
