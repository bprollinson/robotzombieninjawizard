package rznw.game.enemy.action.choice;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemySpellAction;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;

public class RadialHealthBasedSpellChoice extends EnemyActionChoice
{
    private int spellIndex;
    private int radius;

    public RadialHealthBasedSpellChoice(int spellIndex, int radius)
    {
        this.spellIndex = spellIndex;
        this.radius = radius;
    }

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        MapElement mainCharacterMapElement = mainCharacter.getMapElement();
        MapElement enemyMapElement = enemyCharacter.getMapElement();

        if (mainCharacter.getHP() < Math.floor(0.6 * mainCharacter.getMaxHP()))
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
