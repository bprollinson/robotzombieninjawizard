package rznw.map.element;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

public class Blotch extends MapElement
{
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
}
