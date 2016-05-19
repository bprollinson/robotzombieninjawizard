package rznw.game.enemy.action.choice;

import rznw.map.GameWorld;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterWithSpell;
import rznw.game.enemy.action.EnemyAction;
import rznw.game.enemy.action.EnemySpellAction;
import rznw.game.enemy.spell.EnemySpell;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.element.MapElement;

public class PartialHealSpellChoice implements EnemyActionChoice
{
    public EnemyAction getAction(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        System.out.println("In getSpellAction");

        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        MapElement mainCharacterMapElement = mainCharacter.getMapElement();
        MapElement enemyMapElement = enemyCharacter.getMapElement();

        if (enemyCharacter.getHP() > Math.floor(0.6 * enemyCharacter.getMaxHP()))
        {
            System.out.println("Too healthy - just attacking");
            return null;
        }

        System.out.println("Healing!");

        int spellPoints = ((EnemyCharacterWithSpell)enemyCharacter).getSpellPoints(0);

        if (spellPoints == 0)
        {
            return null;
        }

        EnemySpell enemySpell = ((EnemyCharacterWithSpell)enemyCharacter).getSpell(0);
        int MPCost = enemySpell.getMPCost(spellPoints);

        if (MPCost > enemyCharacter.getMP())
        {
            return null;
        }

        return new EnemySpellAction(((EnemyCharacterWithSpell)enemyCharacter).getSpell(0), spellPoints);
    }
}
