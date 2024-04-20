package rznw.game.maincharacter;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.inventory.InventoryFullException;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Potion;
import rznw.game.skill.Skill;
import rznw.game.spell.robot.PowerSearchSpell;
import rznw.game.spell.robot.RobotSpellFactory;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class ItemKillBonusGranter implements KillBonusGranterComponent
{
    public void grantKillBonuses(MainCharacter mainCharacter, EnemyCharacter enemyCharacter)
    {
        if (enemyCharacter.isDroppingItems(mainCharacter))
        {
            InventoryItemGroup itemGroup = new InventoryItemGroup(enemyCharacter.getItemDrop(), 1);

            if (mainCharacter.getStatusEffects().getStatusEffectTurns(TurnBasedStatusEffects.EFFECT_POWER_SEARCH) > 0)
            {
                itemGroup = PowerSearchSpell.getUpgradedItemGroup(itemGroup, mainCharacter.getSpells().getSpellPoints(RobotSpellFactory.SPELL_POWER_SEARCH));
            }

            try
            {
                mainCharacter.getInventory().addItems(itemGroup);
                LogRendererFactory.instance().log("Found " + itemGroup.getItem().getDisplayName().toLowerCase() + ".");
            }
            catch (InventoryFullException ife)
            {
                LogRendererFactory.instance().log("Can't acquire item - inventory full.");
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
                    LogRendererFactory.instance().log("Found potion.");
                }
                catch (InventoryFullException ife)
                {
                    LogRendererFactory.instance().log("Can't find potion - inventory full.");
                }
            }
        }
    }
}
