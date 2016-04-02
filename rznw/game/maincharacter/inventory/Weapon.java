package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;

public abstract class Weapon extends EquipmentItem
{
    public abstract int getDamage();

    public void damagedEnemyCharacter(EnemyCharacter enemyCharacter)
    {
    }
}
