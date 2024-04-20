package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.skill.Skill;

public abstract class EquipmentItem
{
    public abstract String getDisplayName();

    public abstract String[] getStats();

    public abstract int getEquipmentType();

    public int getBuyPrice(MainCharacter character)
    {
        int priceReductionPercent = (int)(2.0 * character.getSkills().getSkillPoints(Skill.SKILL_SUMMON_SHOPKEEPER));

        return (int)Math.ceil((100.0 - priceReductionPercent) / 100.0 * this.getValue());
    }

    public int getSellPrice()
    {
        return (int)Math.floor(0.6 * this.getValue());
    }

    public boolean isWeapon()
    {
        return false;
    }

    public boolean isShield()
    {
        return false;
    }

    public boolean isArmor()
    {
        return false;
    }

    public int getValue()
    {
        return 400;
    }

    public abstract int getEquipmentNumber();
}
