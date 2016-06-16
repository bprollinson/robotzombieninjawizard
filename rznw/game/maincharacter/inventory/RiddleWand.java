package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class RiddleWand extends Weapon
{
    private static final int EQUIPMENT_NUMBER = 10;

    public String getDisplayName()
    {
        return "Riddle Wand";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Confuses enemies",
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
        System.out.println("Enemy is confused by the riddle wand");
        enemyCharacter.getStatusEffects().confuse();
    }

    public int getEquipmentNumber()
    {
        return RiddleWand.EQUIPMENT_NUMBER;
    }
}
