package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
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
}
