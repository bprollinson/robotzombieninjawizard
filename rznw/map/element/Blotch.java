package rznw.map.element;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

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
        enemyCharacter.getStatusEffects().poison();

        StringUtils utils = new StringUtils();
        LogRendererFactory.instance().log(utils.UCFirst(enemyCharacter.getLogName()) + " poisoned by blotch.");
    }

    public int getElementNumber()
    {
        return Blotch.ELEMENT_NUMBER;
    }
}
