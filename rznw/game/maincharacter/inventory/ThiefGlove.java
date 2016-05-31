package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class ThiefGlove extends Weapon
{
    public String getDisplayName()
    {
        return "Thief Glove";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Steals gold from enemies",
            "Gold stolen: 10",
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
        System.out.println("Stealing gold from the enemy");
        mainCharacter.getInventory().addGold(10);
    }
}
