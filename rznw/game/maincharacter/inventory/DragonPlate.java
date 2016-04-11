package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class DragonPlate extends Armor
{
    public String getDisplayName()
    {
        return "Dragon Plate";
    }

    public int getDodgePercent()
    {
        return 4;
    }

    public int getPaddingPercent()
    {
        return 4;
    }

    public int getValue()
    {
        return 200;
    }

    public void damagedByEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Damaged by an enemy character");

        enemyCharacter.damage(10, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_OTHER);
    }
}
