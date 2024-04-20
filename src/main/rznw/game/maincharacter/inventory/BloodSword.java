package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class BloodSword extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 1;

    public String getDisplayName()
    {
        return "Blood Sword";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "",
            "Steals health from enemies",
            "Health stolen: 10% of damage",
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
        int healHP = (int)Math.floor(0.1 * damage);

        int HPHealed = mainCharacter.heal(healHP);
        LogRendererFactory.instance().log("Healed " + HPHealed + " HP.");
    }

    public int getEquipmentNumber()
    {
        return BloodSword.EQUIPMENT_NUMBER;
    }
}
