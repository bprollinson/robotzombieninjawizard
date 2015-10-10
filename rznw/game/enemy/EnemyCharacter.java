package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

public abstract class EnemyCharacter extends Character
{
    public EnemyAIBasedPositionChange getPositionChange(MainCharacter character)
    {
        int enemyRow = this.getMapElement().getRow();
        int enemyColumn = this.getMapElement().getColumn();

        int characterRow = character.getMapElement().getRow();
        int characterColumn = character.getMapElement().getColumn();

        int deltaRow = 0;
        if (enemyRow < characterRow)
        {
            deltaRow = 1;
        }
        if (enemyRow > characterRow)
        {
            deltaRow = -1;
        }

        int deltaColumn = 0;
        if (enemyColumn < characterColumn)
        {
            deltaColumn = 1;
        }
        if (enemyColumn > characterColumn)
        {
            deltaColumn = -1;
        }

        return new EnemyAIBasedPositionChange(this, deltaRow, deltaColumn);
    }

    public int getMaxHP()
    {
        return 10;
    }

    public int getMaxMP()
    {
        return 10;
    }

    public int getDamage()
    {
        return 1;
    }

    public int getNumGold()
    {
        return 1;
    }

    public abstract InventoryItemGroup getItemDrops();

    public int getExperienceReward()
    {
        return 20;
    }
}
