package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.EquipmentFullException;
import rznw.game.maincharacter.inventory.EquipmentGroup;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.spell.robot.PowerSearchSpell;
import rznw.utility.RandomNumberGenerator;

public class KillBonusGranter
{
    public void grantKillBonuses(Character character, Character otherCharacter)
    {
        if (!character.isMainCharacter() || !otherCharacter.isEnemy())
        {
            return;
        }

        MainCharacter mainCharacter = (MainCharacter)character;
        EnemyCharacter enemyCharacter = (EnemyCharacter)otherCharacter;

        this.grantExperience(mainCharacter, enemyCharacter);
        this.grantGold(mainCharacter, enemyCharacter);
        this.grantItems(mainCharacter, enemyCharacter);
        this.grantEquipment(mainCharacter, enemyCharacter);
    }

    private void grantExperience(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        int oldLevel = mainCharacter.getLevel();

        int experience = enemyCharacter.getExperienceReward();
        mainCharacter.grantExperience(experience);
        int newLevel = ExperienceCalculator.getLevel(mainCharacter.getExperience());

        if (newLevel > oldLevel)
        {
            System.out.println("Leveling up " + (newLevel - oldLevel) + " time(s) to level " + newLevel);
            mainCharacter.setLevel(newLevel);
            mainCharacter.setPendingLevels(newLevel - oldLevel);
        }
    }

    private void grantGold(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        int baseGold = enemyCharacter.getNumGold();
        int bonusGoldPercent = RandomNumberGenerator.randomInteger(0, mainCharacter.getSkillPoints(MainCharacter.SKILL_ABUNDANCE));
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

        if (mainCharacter.getSkillPoints(MainCharacter.SKILL_POTION_FIND) > 0)
        {
            int probability = 5 * mainCharacter.getSkillPoints(MainCharacter.SKILL_POTION_FIND);

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
