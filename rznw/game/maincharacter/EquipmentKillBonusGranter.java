package rznw.game.maincharacter;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;

public class EquipmentKillBonusGranter implements KillBonusGranterComponent
{
    public void grantKillBonuses(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        if (enemyCharacter.isDroppingEquipment())
        {
            EquipmentGroup equipmentGroup = new EquipmentGroup(enemyCharacter.getEquipmentDrop(), 1);
            try
            {
                mainCharacter.getEquipment().addEquipment(equipmentGroup);
            }
            catch (EquipmentFullException efe)
            {
                System.out.println("Equipment full");
            }
        }
    }
}
