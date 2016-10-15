package rznw.game.spell.wizard;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.SummonedGolem;
import rznw.map.ClosestSquareCalculator;
import rznw.map.GameWorld;
import rznw.map.MapScraper;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedGolemMapElement;

public class SummonGolemSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Summon Golem";
    }

    public String getDescription()
    {
        return "Summons a golem to fight alongside you. This golem will wander around independently, continuing to battle until its HP are exhausted. Only one golem may be summoned at any time.";
    }

    public boolean canCast(GameWorld gameWorld, int spellPoints)
    {
        MapScraper mapScraper = new MapScraper();

        if (mapScraper.mapContainsElementOfType(gameWorld.getMap(), SummonedGolemMapElement.class))
        {
            return false;
        }

        return super.canCast(gameWorld, spellPoints);
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Summon Golem");

        MapElement element = this.getPositionElement(gameWorld);
        System.out.println("Creating golem at: " + element.getRow() + ", " + element.getColumn());

        int maxHP = 100 + 5 * spellPoints;
        System.out.println("Max HP is: " + maxHP);

        SummonedGolem golem = new SummonedGolem(maxHP);
        SummonedGolemMapElement golemElement = new SummonedGolemMapElement(element.getRow(), element.getColumn(), golem);
        golem.setMapElement(golemElement);
        gameWorld.getMap().setElement(golemElement.getRow(), golemElement.getColumn(), golemElement);
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    private MapElement getPositionElement(GameWorld gameWorld)
    {
        return new ClosestSquareCalculator(gameWorld).getClosestPositionElement(gameWorld.getMainCharacter().getMapElement());
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Golem HP: " + (100 + 5 * spellPoints),
            "Golem damage: 10"
        };
    }
}
