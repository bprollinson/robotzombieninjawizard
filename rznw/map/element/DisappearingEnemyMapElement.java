package rznw.map.element;

import rznw.game.enemy.EnemyCharacter;

public class DisappearingEnemyMapElement extends EnemyMapElement
{
    public DisappearingEnemyMapElement(int row, int column, char displayCharacter, EnemyCharacter enemyCharacter)
    {
        super(row, column, displayCharacter, enemyCharacter);
    }

    public char getDisplayCharacter()
    {
        if (this.character.getStatusEffects().isInvisible())
        {
            return ' ';
        }

        return super.getDisplayCharacter();
    }
}
