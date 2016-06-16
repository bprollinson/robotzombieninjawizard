package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class DeathScythe extends Weapon
{
    private static final int EQUIPMENT_NUMBER = 3;

    public String getDisplayName()
    {
        return "Death Scythe";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Bonus damage: 50% of remaining enemy HP",
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
        System.out.println("Scything opponent!");

        int hp = enemyCharacter.getHP() / 2;
        enemyCharacter.damage(hp, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);
    }

    public int getEquipmentNumber()
    {
        return DeathScythe.EQUIPMENT_NUMBER;
    }
}
