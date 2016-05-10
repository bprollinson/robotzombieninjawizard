package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class Weapon extends EquipmentItem
{
    public abstract int getDamage();

    public int getToHitBonus()
    {
        return 0;
    }

    public boolean isWeapon()
    {
        return true;
    }

    public void step(MainCharacter character)
    {
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
    }
}
