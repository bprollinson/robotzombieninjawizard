package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.skill.Skill;
import rznw.game.spell.robot.PowerSearchSpell;
import rznw.utility.RandomNumberGenerator;

public class KillBonusGranter
{
    private KillBonusGranterComponent[] components;

    public KillBonusGranter()
    {
        this.components = new KillBonusGranterComponent[] {
            new ExperienceKillBonusGranter()
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

        this.grantGold(mainCharacter, enemyCharacter);
        this.grantItems(mainCharacter, enemyCharacter);
        this.grantEquipment(mainCharacter, enemyCharacter);
    }

    private void grantGold(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        int baseGold = enemyCharacter.getNumGold();
        int bonusGoldPercent = RandomNumberGenerator.randomInteger(0, mainCharacter.getSkillPoints(Skill.SKILL_ABUNDANCE));
        bonusGoldPercent += enemyCharacter.getStatusEffects().getBonusGoldPercent();
        int bonusGold = (int)(baseGold * bonusGoldPercent / 100);

        mainCharacter.getInventory().addGold(baseGold + bonusGold);
    }

    private void grantItems(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        if (enemyCharacter.isDroppingItems(mainCharacter))
        {
            InventoryItemGroup itemGroup = new InventoryItemGroup(enemyCharacter.getItemDrop(), 1);

            if (mainCharacter.getStatusEffects().powerSearchEnabled())
            {
                System.out.println("Upgrading item via power search");
                itemGroup = PowerSearchSpell.getUpgradedItemGroup(itemGroup, mainCharacter.getSpellPoints(15));
            }

            try
            {
                mainCharacter.getInventory().addItems(itemGroup);
            }
            catch (InventoryFullException ife)
            {
                System.out.println("Inventory full");
            }
        }

        if (mainCharacter.getSkillPoints(Skill.SKILL_POTION_FIND) > 0)
        {
            int probability = 5 * mainCharacter.getSkillPoints(Skill.SKILL_POTION_FIND);

            if (RandomNumberGenerator.rollSucceeds(probability))
            {
                InventoryItemGroup itemGroup = new InventoryItemGroup(new Potion(), 1);
                try
                {
                    mainCharacter.getInventory().addItems(itemGroup);
                }
                catch (InventoryFullException ife)
                {
                    System.out.println("Inventory full");
                }
            }
        }
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
