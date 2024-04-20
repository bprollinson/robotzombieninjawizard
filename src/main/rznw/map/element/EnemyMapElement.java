package rznw.map.element;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;

public class EnemyMapElement extends CharacterMapElement
{
    public static final int ELEMENT_NUMBER = 3;

    public EnemyMapElement(int row, int column, char displayCharacter, EnemyCharacter enemyCharacter)
    {
        super(row, column, displayCharacter, enemyCharacter);
    }

    public boolean isEnemy()
    {
        return true;
    }

    public int getElementNumber()
    {
        return EnemyMapElement.ELEMENT_NUMBER;
    }

    public boolean interactsWithCharacter(Character character)
    {
        return !character.isEnemy();
    }
}
