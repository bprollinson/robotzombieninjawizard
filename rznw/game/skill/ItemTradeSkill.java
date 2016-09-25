package rznw.game.skill;

import rznw.game.StatusEffects;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.map.GameWorld;

public class ItemTradeSkill extends Skill
{
    public String getDisplayName()
    {
        return "Item Trade";
    }

    public String getDescription()
    {
        return "Allows you to trade in multiple items for a new random item.";
    }

    public boolean canUse(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        if (character.getSkills().getSkillPoints(Skill.SKILL_ITEM_TRADE) == 0)
        {
            return false;
        }

        Inventory inventory = character.getInventory();

        int numItems = 0;

        int numItemGroups = inventory.getNumItemGroups();
        for (int i = 0; i < numItemGroups; i++)
        {
            InventoryItemGroup itemGroup = inventory.getItemGroup(i);
            numItems += itemGroup.getNumItems();
        }

        int skillPoints = character.getSkills().getSkillPoints(Skill.SKILL_ITEM_TRADE);
        int numToTradeIn = 6 - (int)Math.floor(skillPoints / 5);

        return numItems >= numToTradeIn;
    }

    public void use(GameWorld gameWorld)
    {
        MainCharacter character = gameWorld.getMainCharacter();
        int skillPoints = character.getSkills().getSkillPoints(Skill.SKILL_ITEM_TRADE);
        int numToTradeIn = 6 - (int)Math.floor(skillPoints / 5);

        character.getStatusEffects().setStat(StatusEffects.STAT_ITEM_TRADE_NUMBER, numToTradeIn);
    }

    public String[] getStats(MainCharacter character, int skillPoints)
    {
        int numToTradeIn = 6 - (int)Math.floor(skillPoints / 5);

        return new String[] {
            "Trade-in ratio: " + numToTradeIn + ":1"
        };
    }
}
