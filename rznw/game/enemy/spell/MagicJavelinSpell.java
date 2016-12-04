package rznw.game.enemy.spell;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.StringUtils;

public class MagicJavelinSpell extends EnemySpell
{
    public void cast(GameWorld gameWorld, EnemyCharacter enemyCharacter, int spellPoints)
    {
        StringUtils utils = new StringUtils();
        LogRendererFactory.instance().log(utils.UCFirst(enemyCharacter.getLogName()) + " casts magic javelin.");

        int damage = 80 + 20 * spellPoints;

        if (this.mainCharacterInDamagePosition(gameWorld, enemyCharacter))
        {
            MainCharacter mainCharacter = gameWorld.getMainCharacter();
            int damageDealt = mainCharacter.damage(damage, enemyCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            LogRendererFactory.instance().log("Hit for " + damageDealt + " damage.");
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
