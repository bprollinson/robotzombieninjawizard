package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class GauntletOfDarkness extends Weapon
{
    public static final int EQUIPMENT_NUMBER = 4;

    public String getDisplayName()
    {
        return "Gauntlet of Darkness";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Sets enemy HP to 1",
            "Chance to zap HP: 25%",
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
        if (enemyCharacter.getHP() > 1 && RandomNumberGenerator.rollSucceeds(25))
        {
            enemyCharacter.setHP(1);

            LogRendererFactory.instance().log("Enemy HP set to 1.");
        }
    }

    public int getEquipmentNumber()
    {
        return GauntletOfDarkness.EQUIPMENT_NUMBER;
    }
}
