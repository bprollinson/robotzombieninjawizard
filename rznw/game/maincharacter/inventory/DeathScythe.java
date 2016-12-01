package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class DeathScythe extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 3;

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
        int hp = enemyCharacter.getHP() / 2;
        int damageDealt = enemyCharacter.damage(hp, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_PHYSICAL);

        LogRendererFactory.instance().log(damageDealt + " extra damage from death scythe.");
    }

    public int getEquipmentNumber()
    {
        return DeathScythe.EQUIPMENT_NUMBER;
    }
}
