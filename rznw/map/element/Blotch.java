package rznw.map.element;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

public class Blotch extends MapElement
{
    public static final int ELEMENT_NUMBER = 1;

    public Blotch(int row, int column)
    {
        super(row, column);
    }

    public char getDisplayCharacter()
    {
        return ';';
    }

    public void collideWithEnemy(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        System.out.println("Poisoning enemy from blotch");
        enemyCharacter.getStatusEffects().poison();
    }

    public int getElementNumber()
    {
        return Blotch.ELEMENT_NUMBER;
    }
}
