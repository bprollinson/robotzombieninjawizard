package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class DeathScythe extends Weapon
{
    public String getDisplayName()
    {
        return "Death Scythe";
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
        System.out.println("Scything opponent!");

        int hp = enemyCharacter.getHP() / 2;
        enemyCharacter.damage(hp, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);
    }
}
