package rznw.game.maincharacter.inventory;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class PoisonCloth extends Armor
{
    public String getDisplayName()
    {
        return "Leech Mail";
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
        System.out.println("Poisoning enemy with poison cloth");

        enemyCharacter.getStatusEffects().poison();
    }
}
