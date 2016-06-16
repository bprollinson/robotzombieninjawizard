package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public class InvisibilityWand extends Weapon
{
    private static final int EQUIPMENT_NUMBER = 7;

    public String getDisplayName()
    {
        return "Invisibility Wand";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Has a chance to make opponents vanish",
            "Chance to vanish: 5%",
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
        System.out.println("Enemy is hit by the invisibility wand");

        if (RandomNumberGenerator.rollSucceeds(5))
        {
            System.out.println("It makes them disappear");
            enemyCharacter.setHP(0);
        }
    }

    public int getEquipmentNumber()
    {
        return InvisibilityWand.EQUIPMENT_NUMBER;
    }
}
