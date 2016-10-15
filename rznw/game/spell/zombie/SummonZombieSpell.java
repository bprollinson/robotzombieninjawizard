package rznw.game.spell.zombie;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.game.SummonedZombie;
import rznw.map.ClosestSquareCalculator;
import rznw.map.GameWorld;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedZombieMapElement;

public class SummonZombieSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Summon Zombie";
    }

    public String getDescription()
    {
        return "Summons a zombie that will independently battle enemies. This zombie will attack enemies in melee combat. It will continue to battle until its HP are exhausted.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        System.out.println("Casting Summon Zombie");

        MapElement element = this.getPositionElement(gameWorld);
        System.out.println("Creating zombie at: " + element.getRow() + ", " + element.getColumn());

        int maxHP = 100 + 5 * spellPoints;
        System.out.println("Max HP is: " + maxHP);

        SummonedZombie zombie = new SummonedZombie(maxHP);
        SummonedZombieMapElement zombieElement = new SummonedZombieMapElement(element.getRow(), element.getColumn(), zombie);
        zombie.setMapElement(zombieElement);
        gameWorld.getMap().setElement(zombieElement.getRow(), zombieElement.getColumn(), zombieElement);
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
            "Zombie HP: " + (100 + 5 * spellPoints),
            "Zombie damage: 10"
        };
    }
}
