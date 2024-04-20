package rznw.map.element;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;

public class DisappearingEnemyMapElement extends EnemyMapElement
{
    public static final int ELEMENT_NUMBER = 2;

    public DisappearingEnemyMapElement(int row, int column, char displayCharacter, EnemyCharacter enemyCharacter)
    {
        super(row, column, displayCharacter, enemyCharacter);
    }

    public char getDisplayCharacter()
    {
        if (this.character.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_INVISIBLE) > 0)
        {
            return ' ';
        }

        return super.getDisplayCharacter();
    }

    public int getElementNumber()
    {
        return DisappearingEnemyMapElement.ELEMENT_NUMBER;
    }

    public String getMetadata()
    {
        return "" + this.getCharacter().getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_INVISIBLE);
    }
}
