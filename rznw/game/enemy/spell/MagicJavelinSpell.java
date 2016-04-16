package rznw.game.enemy.spell;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;

public class MagicJavelinSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        System.out.println("Enemy is casting magic javelin with spell points of: " + spellPoints);

        int damage = 80 + 20 * spellPoints;

        if (this.mainCharacterInDamagePosition(gameWorld, enemyCharacter))
        {
            System.out.println("Hit!");
            MainCharacter mainCharacter = gameWorld.getMainCharacter();
            mainCharacter.damage(damage, enemyCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
        }
    }

    public int getMPCost(int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    private boolean mainCharacterInDamagePosition(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MapElement enemyMapElement = enemyCharacter.getMapElement();
        MapElement mainCharacterMapElement = gameWorld.getMainCharacter().getMapElement();

        int deltaRow = Math.abs(mainCharacterMapElement.getRow() - enemyMapElement.getRow());
        int deltaColumn = Math.abs(mainCharacterMapElement.getColumn() - enemyMapElement.getColumn());

        return deltaRow == 1 && deltaColumn == 1;
    }
}
