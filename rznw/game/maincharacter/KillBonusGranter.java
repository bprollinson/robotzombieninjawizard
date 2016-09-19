package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;

public class KillBonusGranter
{
    private KillBonusGranterComponent[] components;

    public KillBonusGranter()
    {
        this.components = new KillBonusGranterComponent[] {
            new ExperienceKillBonusGranter(),
            new GoldKillBonusGranter(),
            new ItemKillBonusGranter()
        };
    }

    public void grantKillBonuses(Character character, Character otherCharacter)
    {
        if (!character.isMainCharacter() || !otherCharacter.isEnemy())
        {
            return;
        }

        MainCharacter mainCharacter = (MainCharacter)character;
        EnemyCharacter enemyCharacter = (EnemyCharacter)otherCharacter;

        for (int i = 0; i < this.components.length; i++)
        {
            KillBonusGranterComponent component = this.components[i];
            component.grantKillBonuses(mainCharacter, enemyCharacter);
        }

        this.grantEquipment(mainCharacter, enemyCharacter);
    }

    private void grantEquipment(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
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
