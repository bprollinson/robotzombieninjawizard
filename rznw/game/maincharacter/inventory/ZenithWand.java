package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class ZenithWand extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 15;

    public String getDisplayName()
    {
        return "Zenith Wand";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "",
            "Recovers your MP as you explore new territory",
            "Recovers your MP when you hit an enemy",
            "MP recovered: 1 per step",
            "MP recovered: 20 per hit",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDamage()
    {
        return 8;
    }

    public int getValue()
    {
        return 800;
    }

    public void step(MainCharacter character)
    {
        character.healMP(1);
    }

    public void damagedEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        System.out.println("Hitting enemy with zenith wand");

        mainCharacter.healMP(20);
    }

    public int getEquipmentNumber()
    {
        return ZenithWand.EQUIPMENT_NUMBER;
    }
}
