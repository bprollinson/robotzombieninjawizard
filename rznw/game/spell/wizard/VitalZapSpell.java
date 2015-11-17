package rznw.game.spell.wizard;

import rznw.game.Character;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class VitalZapSpell extends Spell
{
    private KillBonusGranter killBonusGranter;

    public VitalZapSpell()
    {
        this.killBonusGranter = new KillBonusGranter();
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Vital Zap");

        MainCharacter character = gameWorld.getMainCharacter();

        int damagePercentage = 5 * spellPoints;

        int deltaRow = 0;
        int deltaColumn = 0;

        switch(direction)
        {
            case Spell.DIRECTION_UP:
                deltaRow = -1;
                break;
            case Spell.DIRECTION_DOWN:
                deltaRow = 1;
                break;
            case Spell.DIRECTION_LEFT:
                deltaColumn = -1;
                break;
            case Spell.DIRECTION_RIGHT:
                deltaColumn = 1;
                break;
        }

        boolean objectFound = false;
        int row = character.getMapElement().getRow();
        int column = character.getMapElement().getColumn();

        while (!objectFound)
        {
            row += deltaRow;
            column += deltaColumn;

            Map map = gameWorld.getMap();
            MapElement element = map.getElement(row, column);

            if (element != null)
            {
                objectFound = true;
            }

            if (element instanceof EnemyMapElement)
            {
                System.out.println("Hit an enemy");

                Character enemy = ((EnemyMapElement)element).getCharacter();
                System.out.println("Before: " + enemy.getHP());
                int damage = (int)Math.floor(damagePercentage / 100.0 * enemy.getHP());
                enemy.damage(damage);
                System.out.println("After: " + enemy.getHP());

                if (enemy.isDead())
                {
                    this.killBonusGranter.grantKillBonuses(character, enemy);
                    map.setElement(element.getRow(), element.getColumn(), null);
                }
            }
        }
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }
}
