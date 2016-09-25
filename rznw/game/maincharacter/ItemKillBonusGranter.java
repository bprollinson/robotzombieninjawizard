package rznw.game.maincharacter;

import rznw.game.StatusEffects;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.skill.Skill;
import rznw.game.spell.robot.PowerSearchSpell;
import rznw.utility.RandomNumberGenerator;

public class ItemKillBonusGranter implements KillBonusGranterComponent
{
    public void grantKillBonuses(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        if (enemyCharacter.isDroppingItems(mainCharacter))
        {
            InventoryItemGroup itemGroup = new InventoryItemGroup(enemyCharacter.getItemDrop(), 1);

            if (mainCharacter.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_POWER_SEARCH) > 0)
            {
                System.out.println("Upgrading item via power search");
                itemGroup = PowerSearchSpell.getUpgradedItemGroup(itemGroup, mainCharacter.getSpells().getSpellPoints(15));
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

        if (mainCharacter.getSkills().getSkillPoints(Skill.SKILL_POTION_FIND) > 0)
        {
            int probability = 5 * mainCharacter.getSkills().getSkillPoints(Skill.SKILL_POTION_FIND);

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
}
