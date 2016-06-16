package rznw.game.maincharacter.inventory;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;

public class MagicJavelin extends Weapon
{
    private static final int EQUIPMENT_NUMBER = 8;
    private static int DAMAGE = 50;

    public String getDisplayName()
    {
        return "Magic Javelin";
    }

    public String[] getStats()
    {
        return new String[] {
            "Damage: " + this.getDamage(),
            "Performs magic damage to enemies in a diagonal line from a damaged enemy",
            "Additional damage radius: 1",
            "Additional damage: " + MagicJavelin.DAMAGE,
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
        this.damageEnemyInPosition(mainCharacter, gameWorld, -1, -1);
        this.damageEnemyInPosition(mainCharacter, gameWorld, -1, 1);
        this.damageEnemyInPosition(mainCharacter, gameWorld, 1, -1);
        this.damageEnemyInPosition(mainCharacter, gameWorld, 1, 1);
    }

    private void damageEnemyInPosition(MainCharacter mainCharacter, GameWorld gameWorld, int deltaRow, int deltaColumn)
    {
        System.out.println("Attempting javelin jab: " + deltaRow + ", " + deltaColumn);

        MapElement mainCharacterElement = mainCharacter.getMapElement();
        int row = mainCharacter.getMapElement().getRow() + deltaRow;
        int column = mainCharacter.getMapElement().getColumn() + deltaColumn;

        MapElement element = gameWorld.getMap().getElement(row, column);
        if (element != null && element.isEnemy())
        {
            System.out.println("Enemy is jabbed!");

            EnemyCharacter enemyCharacter = (EnemyCharacter)((EnemyMapElement)element).getCharacter();
            enemyCharacter.damage(MagicJavelin.DAMAGE, mainCharacter, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
        }
    }

    public int getEquipmentNumber()
    {
        return MagicJavelin.EQUIPMENT_NUMBER;
    }
}
