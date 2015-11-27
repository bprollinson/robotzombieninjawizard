package rznw.game.spell.robot;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.Spell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.utility.RandomNumberGenerator;

public class ParalyzingBlastSpell extends Spell
{
    public void cast(GameWorld gameWorld, int spellPoints)
    {
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Paralyzing Blast");

        MainCharacter character = gameWorld.getMainCharacter();

        int damage = 20 + 10 * spellPoints;

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
                System.out.println("Direct hit " + element);

                Character enemy = ((EnemyMapElement)element).getCharacter();
                System.out.println("Before: " + enemy.getHP());
                enemy.damage(damage, character, gameWorld);
                System.out.println("After: " + enemy.getHP());

                if (!enemy.isDead())
                {
                    int probabilityToFreeze = 5 * spellPoints;

                    if (RandomNumberGenerator.rollSucceeds(probabilityToFreeze))
                    {
                        System.out.println("Enemy paralyzed");
                        enemy.getStatusEffects().freeze();
                    }
                }
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public boolean requiresDirectionInput()
    {
        return true;
    }
}
