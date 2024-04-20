package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class DragonPlate extends Armor
{
    public static final int EQUIPMENT_NUMBER = 22;

    public String getDisplayName()
    {
        return "Dragon Plate";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "Damages enemies who damage you",
            "Enemy damage: 10",
            "",
            "Value: " + this.getValue()
        };
    }

    public int getDodgePercent()
    {
        return 4;
    }

    public int getPaddingPercent()
    {
        return 4;
    }

    public void damagedByEnemyCharacter(MainCharacter mainCharacter, EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        int damageDealt = enemyCharacter.damage(10, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_OTHER);

        LogRendererFactory.instance().log("Dealt " + damageDealt + " damage to " + enemyCharacter.getLogName() + ".");
    }

    public int getEquipmentNumber()
    {
        return DragonPlate.EQUIPMENT_NUMBER;
    }
}
