package rznw.game.maincharacter;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.ui.LogRendererFactory;

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
                LogRendererFactory.instance().log("Found " + equipmentGroup.getItem().getDisplayName().toLowerCase() + ".");
            }
            catch (EquipmentFullException efe)
            {
                LogRendererFactory.instance().log("Can't acquire equipment - equipment full.");
            }
        }
    }
}
