package rznw.game.maincharacter.inventory;

import rznw.game.SummonedGolem;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.ClosestSquareCalculator;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedGolemMapElement;

public class WandOfSummoning extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 13;

    public String getDisplayName()
    {
        return "Wand of Summoning";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Summons golems on contact",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 8;
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Summon golem via wand of summoning");

        MapElement element = this.getPositionElement(gameWorld);
        System.out.println("Creating golem at: " + element.getRow() + ", " + element.getColumn());

        int spellPoints = 5;
        int maxHP = 100 + 5 * spellPoints;
        System.out.println("Max HP is: " + maxHP);

        SummonedGolem golem = new SummonedGolem(maxHP);
        SummonedGolemMapElement golemElement = new SummonedGolemMapElement(element.getRow(), element.getColumn(), golem);
        golem.setMapElement(golemElement);
        gameWorld.getMap().setElement(golemElement.getRow(), golemElement.getColumn(), golemElement);
    }

    private MapElement getPositionElement(GameWorld gameWorld)
    {
        return new ClosestSquareCalculator(gameWorld).getClosestPositionElement(gameWorld.getMainCharacter().getMapElement());
    }

    public int getEquipmentNumber()
    {
        return WandOfSummoning.EQUIPMENT_NUMBER;
    }
}
