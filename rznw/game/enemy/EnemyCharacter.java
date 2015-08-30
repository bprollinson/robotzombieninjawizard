package rznw.game.enemy;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.turn.EnemyAIBasedPositionChange;

public abstract class EnemyCharacter extends Character
{
    public EnemyAIBasedPositionChange getPositionChange(MainCharacter character)
    {
        return new EnemyAIBasedPositionChange(this, -1, -1);
    }
}
