package rznw.game.enemy.action;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.Zenith;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;

public class ZenithActionCalculator extends EnemyActionCalculator
{
    private static final int RADIUS = 2;

    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        EnemyAction confusionPositionChange = this.getConfusionPositionChange(gameWorld, enemyCharacter);
        if (confusionPositionChange != null)
        {
            return confusionPositionChange;
        }

        EnemyAction spellAction = this.getHealSpellAction(enemyCharacter);
        if (spellAction != null)
        {
            return spellAction;
        }

        spellAction = this.getHealthZapSpellAction(gameWorld, enemyCharacter);
        if (spellAction != null)
        {
            return spellAction;
        }

        spellAction = this.getZapSpellAction(gameWorld, enemyCharacter);
        if (spellAction != null)
        {
            return spellAction;
        }

        return this.getPathBasedPositionChange(gameWorld, enemyCharacter);
    }

    private EnemyAction getHealSpellAction(EnemyCharacter enemyCharacter)
    {
        if (enemyCharacter.getHP() > Math.floor(0.6 * enemyCharacter.getMaxHP()))
        {
            System.out.println("Too healthy");
            return null;
        }

        int spellPoints = ((EnemyCharacterWithSpell)enemyCharacter).getSpellPoints(Zenith.SPELL_HEAL);

        if (spellPoints == 0)
        {
            return null;
        }

        EnemySpell enemySpell = ((EnemyCharacterWithSpell)enemyCharacter).getSpell(Zenith.SPELL_HEAL);
        int MPCost = enemySpell.getMPCost(spellPoints);

        if (MPCost > enemyCharacter.getMP())
        {
            return null;
        }

        return new EnemySpellAction(((EnemyCharacterWithSpell)enemyCharacter).getSpell(Zenith.SPELL_HEAL), spellPoints);
    }

    private EnemyAction getHealthZapSpellAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        MapElement mainCharacterMapElement = mainCharacter.getMapElement();
        MapElement enemyMapElement = enemyCharacter.getMapElement();

        if (mainCharacter.getHP() < Math.floor(0.6 * mainCharacter.getMaxHP()))
        {
            System.out.println("Main character too damaged");
            return null;
        }

        if (Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow()) > ZenithActionCalculator.RADIUS || Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn()) > ZenithActionCalculator.RADIUS)
        {
            System.out.println("Main character too far away");
            return null;
        }

        int spellPoints = ((EnemyCharacterWithSpell)enemyCharacter).getSpellPoints(Zenith.SPELL_HEALTH_ZAP);

        if (spellPoints == 0)
        {
            return null;
        }

        EnemySpell enemySpell = ((EnemyCharacterWithSpell)enemyCharacter).getSpell(Zenith.SPELL_HEALTH_ZAP);
        int MPCost = enemySpell.getMPCost(spellPoints);

        if (MPCost > enemyCharacter.getMP())
        {
            return null;
        }

        return new EnemySpellAction(((EnemyCharacterWithSpell)enemyCharacter).getSpell(Zenith.SPELL_HEALTH_ZAP), spellPoints);
    }

    private EnemyAction getZapSpellAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        MapElement mainCharacterMapElement = mainCharacter.getMapElement();
        MapElement enemyMapElement = enemyCharacter.getMapElement();

        int deltaRow = 0;
        int deltaColumn = 0;

        if (mainCharacterMapElement.getRow() != enemyMapElement.getRow())
        {
            deltaRow = mainCharacterMapElement.getRow() < enemyMapElement.getRow() ? -1 : 1;
        }

        if (mainCharacterMapElement.getColumn() != enemyMapElement.getColumn())
        {
            deltaColumn = mainCharacterMapElement.getColumn() < enemyMapElement.getColumn() ? -1 : 1;
        }

        if (deltaRow != 0 && deltaColumn != 0)
        {
            return null;
        }

        Map map = gameWorld.getMap();
        boolean obstacleFound = false;

        int row = enemyMapElement.getRow() + deltaRow;
        int column = enemyMapElement.getColumn() + deltaColumn;
        int targetRow = mainCharacterMapElement.getRow();
        int targetColumn = mainCharacterMapElement.getColumn();

        while (row != targetRow || column != targetColumn)
        {
            if (map.getElement(row, column) != null)
            {
                obstacleFound = true;
                break;
            }

            row += deltaRow;
            column += deltaColumn;
        }

        if (obstacleFound)
        {
            return null;
        }

        int spellPoints = ((EnemyCharacterWithSpell)enemyCharacter).getSpellPoints(Zenith.SPELL_ZAP);

        if (spellPoints == 0)
        {
            return null;
        }

        EnemySpell enemySpell = ((EnemyCharacterWithSpell)enemyCharacter).getSpell(Zenith.SPELL_ZAP);
        int MPCost = enemySpell.getMPCost(spellPoints);

        if (MPCost > enemyCharacter.getMP())
        {
            return null;
        }

        return new EnemySpellAction(((EnemyCharacterWithSpell)enemyCharacter).getSpell(Zenith.SPELL_ZAP), spellPoints);
    }
}
