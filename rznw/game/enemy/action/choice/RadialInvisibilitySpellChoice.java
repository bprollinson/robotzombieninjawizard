package rznw.game.enemy.action.choice;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemySpellAction;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.StatusEffects;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;

public class RadialInvisibilitySpellChoice extends EnemyActionChoice
{
    private int spellIndex;
    private int radius;

    public RadialInvisibilitySpellChoice(int spellIndex, int radius)
    {
        this.spellIndex = spellIndex;
        this.radius = radius;
    }

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        System.out.println("In getSpellAction");

        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        MapElement mainCharacterMapElement = mainCharacter.getMapElement();
        MapElement enemyMapElement = enemyCharacter.getMapElement();

        if (enemyCharacter.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_INVISIBLE) > 0)
        {
            return null;
        }

        if (Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow()) > this.radius || Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn()) > this.radius)
        {
            return null;
        }

        if (!this.canCastSpell(enemyCharacter, this.spellIndex))
        {
            return null;
        }

        int spellPoints = ((EnemyCharacterWithSpell)enemyCharacter).getSpellPoints(this.spellIndex);

        return new EnemySpellAction(((EnemyCharacterWithSpell)enemyCharacter).getSpell(this.spellIndex), spellPoints);
    }
}
