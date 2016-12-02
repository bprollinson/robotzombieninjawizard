package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.ui.LogRendererFactory;

public class PoisonCloth extends Armor
{
    public static final int EQUIPMENT_NUMBER = 24;

    public String getDisplayName()
    {
        return "Poison Cloth";
    }

    public String[] getStats()
    {
        return new String[] {
            "Dodge: " + this.getDodgePercent() + "%",
            "Padding: " + this.getPaddingPercent() + "%",
            "Poisons attacking enemies when they damage you",
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
        enemyCharacter.getStatusEffects().poison();

        LogRendererFactory.instance().log("Enemy poisoned.");
    }

    public int getEquipmentNumber()
    {
        return PoisonCloth.EQUIPMENT_NUMBER;
    }
}
