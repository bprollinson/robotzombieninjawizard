package rznw.game.spell.zombie;

import java.util.Collection;
import java.util.Iterator;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.DirectedSpell;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.turn.positionchange.SpellBasedPositionChange;

public class PoisonCloudSpell extends DirectedSpell
{
    public String getDisplayName()
    {
        return "Poison Cloud";
    }

    public String getDescription()
    {
        return "Poisons all enemies within an area. The area can be in any of the four directions from you.";
    }

    public void cast(GameWorld gameWorld, int spellPoints, int direction)
    {
        System.out.println("Casting Poison Cloud");

        Map map = gameWorld.getMap();

        int radius = 1 + (int)Math.floor(spellPoints / 4);
        System.out.println("Radius is: " + radius);

        SpellBasedPositionChange positionChange = new SpellBasedPositionChange(direction);

        MapElement characterElement = gameWorld.getMainCharacter().getMapElement();

        System.out.println("Character position is: " + characterElement.getRow() + ", " + characterElement.getColumn());

        int row = characterElement.getRow() + positionChange.getDeltaRow() * (radius + 1);
        int column = characterElement.getColumn() + positionChange.getDeltaColumn() * (radius + 1);

        System.out.println("Center row and column are: " + row + ", " + column);

        Collection<EnemyCharacter> enemies = map.getEnemiesInRectangle(characterElement.getRow() - radius, characterElement.getColumn() - radius, characterElement.getRow() + radius, characterElement.getColumn() + radius);
        for (Iterator iterator = enemies.iterator(); iterator.hasNext();)
        {
            System.out.println("Poisoning an enemy");

            EnemyCharacter enemy = (EnemyCharacter)iterator.next();
            enemy.getStatusEffects().poison();
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int radius = 1 + (int)Math.floor(spellPoints / 4);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Radius: " + radius,
            "Position: " + (radius + 1) + " squares from character",
            "Poison damage: 10 per turn"
        };
    }
}
