package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public abstract class Armor extends EquipmentItem
{
    public abstract int getDodgePercent();

    public abstract int getPaddingPercent();

    public int getThickSkinBonus()
    {
        return 0;
    }

    public boolean isArmor()
    {
        return true;
    }

    public void step(MainCharacter character)
    {
    }

    public void damagedByEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
    }
}
