package rznw.game.enemy.action.choice;

import rznw.map.GameWorld;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemySpellAction;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.MapElement;

public class PartialHealSpellChoice extends EnemyActionChoice
{
    private int spellIndex;

    public PartialHealSpellChoice(int spellIndex)
    {
        this.spellIndex = spellIndex;
    }

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        System.out.println("Zenith healing?");

        if (enemyCharacter.getHP() > Math.floor(0.6 * enemyCharacter.getMaxHP()))
        {
            System.out.println("Too healthy");
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
