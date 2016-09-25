package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.calculator.MainCharacterDamageDealtCalculator;
import rznw.game.maincharacter.calculator.MainCharacterDamageReceivedCalculator;
import rznw.game.maincharacter.calculator.MainCharacterDodgeCalculator;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.StatusEffects;
import rznw.game.skill.Skill;
import rznw.game.skill.SkillFactory;
import rznw.game.spell.SpellFactory;
import rznw.game.stat.Stat;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public abstract class MainCharacter extends Character
{
    public static final int SKILL_POINTS_PER_LEVEL = 4;
    public static final int SPELL_POINTS_PER_LEVEL = 4;

    public static final int MAX_LEVEL = 80;
    public static final int MAX_SKILL_POINTS = 20;
    public static final int MAX_SPELL_POINTS = 20;

    private static String[] skillCategory = {
        "Vitality",
        "Agility",
        "Fortitude",
        "Magic"
    };

    private int level = 0;
    private int experience = 0;
    private int pendingLevels = 0;

    private MainCharacterStats stats;

    private int[] skills;
    private int[] spells;
    private Inventory inventory;
    private Equipment equipment;

    private int HPSteps = 0;
    private int MPSteps = 0;
    private int manaRiverSteps = 0;

    public MainCharacter()
    {
        super(20, 20);

        this.stats = new MainCharacterStats();

        this.skills = new int[16];
        this.spells = new int[16];

        for (int i = 0; i < this.skills.length; i++)
        {
            this.skills[i] = 0;
            this.spells[i] = 0;
        }

        this.inventory = new Inventory(this);
        this.equipment = new Equipment(this);

        this.HP = this.getMaxHP();
        this.MP = this.getMaxMP();
    }

    public MainCharacterStats getStats()
    {
        return this.stats;
    }

    public static String getSkillCategory(int categoryNumber)
    {
        return MainCharacter.skillCategory[categoryNumber];
    }

    public static String getSkillName(int skillNumber)
    {
        return MainCharacter.getSkillFactory().getSkill(skillNumber).getDisplayName();
    }

    public static String getSkillDescription(int skillNumber)
    {
        return MainCharacter.getSkillFactory().getSkill(skillNumber).getDescription();
    }

    public abstract String getSpellCategory(int categoryNumber);

    public String getSpellName(int spellNumber)
    {
        return this.getSpellFactory().getSpell(spellNumber).getDisplayName();
    }

    public String getSpellDescription(int spellNumber)
    {
        return this.getSpellFactory().getSpell(spellNumber).getDescription();
    }

    public int getLevel()
    {
        return this.level;
    }

    public int getExperience()
    {
        return this.experience;
    }

    public int getMaxHP()
    {
        return 200 + 20 * this.stats.getStatPoints(Stat.STAT_HEALTH);
    }

    public int getMaxMP()
    {
        return 200 + 20 * this.stats.getStatPoints(Stat.STAT_MANA);
    }

    public int getDamage()
    {
        return new MainCharacterDamageDealtCalculator().getDamage(this);
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }

    public Equipment getEquipment()
    {
        return this.equipment;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setPendingLevels(int pendingLevels)
    {
        this.pendingLevels = pendingLevels;
    }

    public int getPendingLevels()
    {
        return this.pendingLevels;
    }

    public void grantExperience(int experience)
    {
        this.experience += experience;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;
    }

    public void addSkillPoint(int skillNumber)
    {
        this.skills[skillNumber]++;
    }

    public int getSkillPoints(int skillNumber)
    {
        return this.skills[skillNumber];
    }

    public void setSkillPoints(int skillNumber, int points)
    {
        this.skills[skillNumber] = points;
    }

    public void addSpellPoint(int spellNumber)
    {
        this.spells[spellNumber]++;
    }

    public int getSpellPoints(int spellNumber, boolean allowAdjustment)
    {
        if (!allowAdjustment)
        {
            return this.spells[spellNumber];
        }

        return this.getSpellPoints(spellNumber);
    }

    public int getSpellPoints(int spellNumber)
    {
        int spellPoints = this.spells[spellNumber];

        int bonusSpellPoints = 0;

        if (this.getStatusEffects().getStatusEffectTurns(StatusEffects.EFFECT_MAGIC_SEEDS) > 0)
        {
            int magicSeedPoints = this.getSkillPoints(Skill.SKILL_MAGIC_SEEDS);
            bonusSpellPoints = (int)Math.floor(magicSeedPoints / 4);

            System.out.println("Bonus spell points: " + bonusSpellPoints);
            spellPoints += bonusSpellPoints;
        }

        return Math.min(spellPoints, MainCharacter.MAX_SPELL_POINTS);
    }

    public void setSpellPoints(int spellNumber, int points)
    {
        this.spells[spellNumber] = points;
    }

    public void resetStateAfterLevelUp()
    {
        this.fillHP();
        this.fillMP();
    }

    public abstract String getCharacterClass();

    public abstract int getCharacterClassNumber();

    public void useItem(int itemIndex, GameWorld gameWorld)
    {
        InventoryItem item = this.inventory.getItemGroup(itemIndex).getItem();
        this.inventory.removeItems(new InventoryItemGroup(item, 1));
        item.useOnCharacter(this, gameWorld);
    }

    public abstract SpellFactory getSpellFactory();

    public static SkillFactory getSkillFactory()
    {
        return new SkillFactory();
    }

    public boolean meleeAttackHits()
    {
        int toHitPercent = 50 + 2 * this.stats.getStatPoints(Stat.STAT_ACCURACY);

        Weapon weapon = this.getEquipment().getEquippedWeapon();
        if (weapon != null)
        {
            System.out.println("Adjusting to hit percent");
            toHitPercent += weapon.getToHitBonus();
        }

        return RandomNumberGenerator.rollSucceeds(toHitPercent);
    }

    public boolean dodgesAttack()
    {
        return new MainCharacterDodgeCalculator().dodgesAttack(this);
    }

    public int getHPSteps()
    {
        return this.HPSteps;
    }

    public void setHPSteps(int HPSteps)
    {
        this.HPSteps = HPSteps;
    }

    public int getMPSteps()
    {
        return this.MPSteps;
    }

    public void setMPSteps(int MPSteps)
    {
        this.MPSteps = MPSteps;
    }

    public int getManaRiverSteps()
    {
        return this.manaRiverSteps;
    }

    public void setManaRiverSteps(int manaRiverSteps)
    {
        this.manaRiverSteps = manaRiverSteps;
    }

    public int getStepsForHeal()
    {
        return Math.max(1, 20 - this.stats.getStatPoints(Stat.STAT_PHYSICAL_REGENERATION));
    }

    public int getStepsForMPHeal()
    {
        return Math.max(1, 20 - this.stats.getStatPoints(Stat.STAT_MENTAL_REGENERATION));
    }

    public int getStepsForManaRiver()
    {
        return Math.max(1, 20 - this.getSkillPoints(Skill.SKILL_MANA_RIVER));
    }

    public void incrementSteps()
    {
        new MainCharacterStepIncrementer().incrementSteps(this);
    }

    public int getViewRadius()
    {
        int equipmentBonus = 0;

        Shield shield = this.getEquipment().getEquippedShield();
        if (shield != null)
        {
            equipmentBonus = shield.getViewRadiusBonus();
        }

        return 2 + this.stats.getStatPoints(Stat.STAT_SIGHT) + equipmentBonus;
    }

    public int damage(int damage, Character damageSource, GameWorld gameWorld, int damageSourceType)
    {
        damage = new MainCharacterDamageReceivedCalculator().getDamage(this, damage, damageSource, damageSourceType);

        if (damage < 0) {
            this.HP += (-damage);
            return 0;
        }

        if (damage == 0)
        {
            return damage;
        }

        this.HP -= damage;

        new MainCharacterDamageResponder().respondToDamage(gameWorld, this, damageSource, damage);

        return damage;
    }

    public void heal(int HP)
    {
        int bonusHPPercent = 5 * this.stats.getStatPoints(Stat.STAT_LIFE_BOND);
        int bonusHP = (int)Math.floor(bonusHPPercent / 100.0 * HP);

        if (bonusHP > 0)
        {
            System.out.println("Bonus HP: " + bonusHP);
        }

        super.heal(HP + bonusHP);
    }

    public boolean isMainCharacter()
    {
        return true;
    }

    public void damagedEnemyCharacter(EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        Weapon weapon = this.equipment.getEquippedWeapon();

        if (weapon != null)
        {
            weapon.damagedEnemyCharacter(this, enemyCharacter, damage, gameWorld);
        }
    }

    public void damagedByEnemyCharacter(EnemyCharacter enemyCharacter, int damage, GameWorld gameWorld)
    {
        Armor armor = this.equipment.getEquippedArmor();

        if (armor != null)
        {
            armor.damagedByEnemyCharacter(this, enemyCharacter, damage, gameWorld);
        }
    }
}
