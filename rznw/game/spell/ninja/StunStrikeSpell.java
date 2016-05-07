package rznw.game.spell.ninja;

import rznw.game.Character;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;
import rznw.utility.RandomNumberGenerator;

public class StunStrikeSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Stun Strike";
    }

    public String getDescription()
    {
        return "You lunge in a straight line until an enemy is encountered. You damage that enemy, with a chance to stun it.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Stun Strike");

        MainCharacter character = gameWorld.getMainCharacter();

        int startRow = character.getMapElement().getRow();
        int startColumn = character.getMapElement().getColumn();

        Map map = gameWorld.getMap();
        map.setElement(startRow, startColumn, null);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(0, 0, direction);

        int enemyRow = startRow;
        int enemyColumn = startColumn;

        while (map.getElement(enemyRow, enemyColumn) == null)
        {
            enemyRow += positionChange.getDeltaRow();
            enemyColumn += positionChange.getDeltaColumn();
        }

        int characterRow = enemyRow - positionChange.getDeltaRow();
        int characterColumn = enemyColumn - positionChange.getDeltaColumn();

        MapElement characterElement = character.getMapElement();
        characterElement.setRow(characterRow);
        characterElement.setColumn(characterColumn);
        map.setElement(characterRow, characterColumn, characterElement);

        MapElement enemyElement = map.getElement(enemyRow, enemyColumn);
        if (enemyElement instanceof EnemyMapElement)
        {
            System.out.println("Hitting enemy");
            int damage = 20 + 10 * spellPoints;
            int stunProbability = 5 * spellPoints;

            Character enemy = ((EnemyMapElement)enemyElement).getCharacter();
            System.out.println("Enemy HP before: " + enemy.getHP());
            enemy.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("Enemy HP after: " + enemy.getHP());

            if (RandomNumberGenerator.rollSucceeds(stunProbability))
            {
                System.out.println("Stunning enemy");
                enemy.getStatusEffects().freeze();
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Damage: " + (20 + 10 * spellPoints),
            "Chance to stun: " + 5 * spellPoints + "%"
        };
    }
}
