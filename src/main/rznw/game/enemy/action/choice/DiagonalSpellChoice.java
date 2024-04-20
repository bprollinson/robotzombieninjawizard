package rznw.game.enemy.action.choice;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemySpellAction;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;

public class DiagonalSpellChoice extends EnemyActionChoice
{
    private int spellIndex;

    public DiagonalSpellChoice(int spellIndex)
    {
        this.spellIndex = spellIndex;
    }

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        MapElement mainCharacterMapElement = mainCharacter.getMapElement();
        MapElement enemyMapElement = enemyCharacter.getMapElement();

        int deltaRow = 0;
        int deltaColumn = 0;

        if (mainCharacterMapElement.getRow() != enemyMapElement.getRow())
        {
            deltaRow = Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow());
        }

        if (mainCharacterMapElement.getColumn() != enemyMapElement.getColumn())
        {
            deltaColumn = Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn());
        }

        if (deltaRow != 1 || deltaColumn != 1)
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
