package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.calculator.MainCharacterDamageDealtCalculator;
import rznw.game.maincharacter.calculator.MainCharacterDamageReceivedCalculator;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Armor;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.skill.SkillFactory;
import rznw.game.spell.SpellFactory;
import rznw.game.stat.StatFactory;
import rznw.game.spell.ninja.SmokeBombSpell;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

public abstract class MainCharacter extends Character
{
    public static final int STAT_POINTS_PER_LEVEL = 4;
    public static final int SKILL_POINTS_PER_LEVEL = 4;
    public static final int SPELL_POINTS_PER_LEVEL = 4;

    public static final int MAX_LEVEL = 80;
    public static final int MAX_STAT_POINTS = 20;
    public static final int MAX_SKILL_POINTS = 20;
    public static final int MAX_SPELL_POINTS = 20;

    public static final int STAT_HEALTH = 0;
    public static final int STAT_PHYSICAL_REGENERATION = 1;
    public static final int STAT_LAST_BREATH = 2;
    public static final int STAT_LIFE_BOND = 3;
    public static final int STAT_ACCURACY = 4;
    public static final int STAT_DODGE = 5;
    public static final int STAT_SIGHT = 6;
    public static final int STAT_FIND_TRAPS = 7;
    public static final int STAT_DAMAGE = 8;
    public static final int STAT_PADDING = 9;
    public static final int STAT_UNENCUMBERANCE = 10;
    public static final int STAT_THICK_SKIN = 11;
    public static final int STAT_MANA = 12;
    public static final int STAT_MENTAL_REGENERATION = 13;
    public static final int STAT_MANA_BURN = 14;
    public static final int STAT_MAGIC_RESISTANCE = 15;

    public static final int SKILL_DETECT_VITALITY = 0;
    public static final int SKILL_DETECT_ENEMIES = 1;
    public static final int SKILL_BLOOD_RAGE = 2;
    public static final int SKILL_POTION_FIND = 3;
    public static final int SKILL_SUMMON_SHOPKEEPER = 4;
    public static final int SKILL_WAYPOINT = 5;
    public static final int SKILL_FAST_HANDS = 6;
    public static final int SKILL_FIND_STAIRS = 7;
    public static final int SKILL_ITEM_TRADE = 8;
    public static final int SKILL_RAGE = 9;
    public static final int SKILL_ABUNDANCE = 10;
    public static final int SKILL_DISARM_TRAPS = 11;
    public static final int SKILL_MAGIC_SEEDS = 12;
    public static final int SKILL_MANA_SUCK = 13;
    public static final int SKILL_PROTECTIVE_FIELD = 14;
    public static final int SKILL_MANA_RIVER = 15;

    private static String[] statCategory = {
        "Vitality",
        "Agility",
        "Fortitude",
        "Magic"
    };

    private static String[] skillCategory = {
        "Vitality",
        "Agility",
        "Fortitude",
        "Magic"
    };

    private int level = 0;
    private int experience = 0;
    private int pendingLevels = 0;

    private int[] stats;
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

        this.stats = new int[16];
        this.skills = new int[16];
        this.spells = new int[16];

        for (int i = 0; i < this.stats.length; i++)
        {
            this.stats[i] = 0;
            this.skills[i] = 0;
            this.spells[i] = 0;
        }

        this.inventory = new Inventory(this);
        this.equipment = new Equipment(this);

        this.HP = this.getMaxHP();
        this.MP = this.getMaxMP();
    }

    public static String getStatCategory(int categoryNumber)
    {
        return MainCharacter.statCategory[categoryNumber];
    }

    public static String getStatName(int statNumber)
    {
        return MainCharacter.getStatFactory().getStat(statNumber).getDisplayName();
    }

    public static String getStatDescription(int statNumber)
    {
        return MainCharacter.getStatFactory().getStat(statNumber).getDescription();
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
        return 200 + 20 * this.stats[MainCharacter.STAT_HEALTH];
    }

    public int getMaxMP()
    {
        return 200 + 20 * this.stats[MainCharacter.STAT_MANA];
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

    public void addStatPoint(int statNumber)
    {
        this.stats[statNumber]++;
    }

    public int getStatPoints(int statNumber)
    {
        return this.stats[statNumber];
    }

    public void setStatPoints(int statNumber, int points)
    {
        this.stats[statNumber] = points;
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

        if (this.getStatusEffects().magicSeedsEnabled())
        {
            int magicSeedPoints = this.getSkillPoints(MainCharacter.SKILL_MAGIC_SEEDS);
            bonusSpellPoints = (int)Math.floor(magicSeedPoints / 4);

            System.out.println("Bonus spell points: " + bonusSpellPoints);
            spellPoints += bonusSpellPoints;
        }

        return spellPoints;
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

    public static StatFactory getStatFactory()
    {
        return new StatFactory();
    }

    public abstract SpellFactory getSpellFactory();

    public static SkillFactory getSkillFactory()
    {
        return new SkillFactory();
    }

    public boolean meleeAttackHits()
    {
        int toHitPercent = 50 + 2 * this.getStatPoints(MainCharacter.STAT_ACCURACY);

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
        int toDodgePercent = 2 * this.getStatPoints(MainCharacter.STAT_DODGE);

        Shield shield = this.getEquipment().getEquippedShield();
        if (shield != null)
        {
            int shieldDodgePercent = shield.getDodgePercent();
            System.out.println("Additional shield chance to dodge: " + shieldDodgePercent);
            toDodgePercent += shieldDodgePercent;
        }

        Armor armor = this.getEquipment().getEquippedArmor();
        if (armor != null)
        {
            int armorDodgePercent = armor.getDodgePercent();
            System.out.println("Additional armor chance to dodge: " + armorDodgePercent);
            toDodgePercent += armorDodgePercent;
        }

        if (this.getStatusEffects().rageEnabled())
        {
            int dodgePenalty = Math.max(21 - this.getSkillPoints(MainCharacter.SKILL_RAGE), 1);
            System.out.println("Dodge penalty: " + dodgePenalty);
            toDodgePercent -= dodgePenalty;
        }

        if (this.getStatusEffects().meatShieldEnabled())
        {
            int meatShieldDodgePercent = this.getStatusEffects().getMeatShieldDodgePercent();
            System.out.println("Meat shield dodge bonus: " + meatShieldDodgePercent);
            toDodgePercent += meatShieldDodgePercent;
        }

        return RandomNumberGenerator.rollSucceeds(toDodgePercent);
    }

    private int getStepsForHeal()
    {
        return Math.max(1, 20 - this.getStatPoints(MainCharacter.STAT_PHYSICAL_REGENERATION));
    }

    private int getStepsForMPHeal()
    {
        return Math.max(1, 20 - this.getStatPoints(MainCharacter.STAT_MENTAL_REGENERATION));
    }

    private int getStepsForManaRiver()
    {
        return Math.max(1, 20 - this.getSkillPoints(MainCharacter.SKILL_MANA_RIVER));
    }

    public void incrementSteps()
    {
        if (this.getStatPoints(MainCharacter.STAT_PHYSICAL_REGENERATION) > 0)
        {
            this.HPSteps++;
            if (this.HPSteps >= this.getStepsForHeal())
            {
                this.heal(10);
                this.HPSteps = 0;
            }
        }

        if (this.getStatPoints(MainCharacter.STAT_MENTAL_REGENERATION) > 0)
        {
            this.MPSteps++;
            if (this.MPSteps >= this.getStepsForMPHeal())
            {
                this.healMP(10);
                this.MPSteps = 0;
            }
        }

        if (this.getSkillPoints(MainCharacter.SKILL_MANA_RIVER) > 0)
        {
            this.manaRiverSteps++;
            if (this.manaRiverSteps >= this.getStepsForManaRiver())
            {
                int manaRiverProbability = this.getSkillPoints(MainCharacter.SKILL_MANA_RIVER);

                if (RandomNumberGenerator.rollSucceeds(manaRiverProbability))
                {
                    this.MP = this.getMaxMP();
                }

                this.manaRiverSteps = 0;
            }
        }

        Weapon weapon = this.getEquipment().getEquippedWeapon();
        if (weapon != null)
        {
            weapon.step(this);
        }

        Armor armor = this.getEquipment().getEquippedArmor();
        if (armor != null)
        {
            armor.step(this);
        }
    }

    public int getViewRadius()
    {
        int equipmentBonus = 0;

        Shield shield = this.getEquipment().getEquippedShield();
        if (shield != null)
        {
            equipmentBonus = shield.getViewRadiusBonus();
        }

        return 2 + this.getStatPoints(MainCharacter.STAT_SIGHT) + equipmentBonus;
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

        if (this.getStatusEffects().manaSuckEnabled())
        {
            int MPFromDamage = (int)Math.floor(5.0 / 100.0 * this.getSkillPoints(MainCharacter.SKILL_MANA_SUCK) * damage);
            if (MPFromDamage > 0)
            {
                System.out.println("Healing MP from damage: " + MPFromDamage);
                this.healMP(MPFromDamage);
            }
        }

        if (damageSource == null)
        {
            return damage;
        }

        if (this.getStatusEffects().isCounterstriking() && damageSource.isEnemy())
        {
            System.out.println("Checking counterstrike");

            int counterstrikeProbability = 10 * this.getSpellPoints(13);
            if (RandomNumberGenerator.rollSucceeds(counterstrikeProbability))
            {
                System.out.println("Damaging with counterstrike");

                int counterstrikeDamage = 10 * this.getSpellPoints(13);
                System.out.println("Enemy hp before: " + damageSource.getHP());
                damageSource.damage(counterstrikeDamage, this, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
                System.out.println("Enemy hp after: " + damageSource.getHP());
            }

            this.getStatusEffects().disableCounterstrike();
        }

        if (this.getStatusEffects().thornSkinEnabled() && damageSource.isEnemy())
        {
            System.out.println("Attacking back with thorn skin");

            int thornSkinDamage = 5 * this.getSpellPoints(2);
            System.out.println("Enemy hp before: " + damageSource.getHP());
            damageSource.damage(thornSkinDamage, this, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("Enemy hp after: " + damageSource.getHP());
        }

        if (this.getStatusEffects().poisonSkinEnabled() && damageSource.isEnemy())
        {
            System.out.println("Poisoning with poison skin");

            damageSource.getStatusEffects().poison();
        }

        if (this.getStatusEffects().isDeathStriking() && damageSource.isEnemy())
        {
            System.out.println("Checking death strike");

            int deathStrikeProbability = 5 * this.getSpellPoints(15);
            if (RandomNumberGenerator.rollSucceeds(deathStrikeProbability))
            {
                System.out.println("Killing with death strike");
                damageSource.setHP(0);
            }

            this.getStatusEffects().disableDeathStrike();
        }

        if (this.getStatusEffects().smokeBombEnabled() && damageSource.isEnemy())
        {
            System.out.println("Checking smoke bomb");

            int smokeBombProbability = 5 * this.getSpellPoints(12);
            if (RandomNumberGenerator.rollSucceeds(smokeBombProbability))
            {
                System.out.println("Escaping with smoke bomb");
                SmokeBombSpell.escape(gameWorld);
            }

            this.getStatusEffects().disableSmokeBomb();
        }

        if (this.getStatusEffects().barbedSkinEnabled() && damageSource.isEnemy())
        {
            System.out.println("Checking barbed skin");

            int barbedSkinProbability = 5 * this.getSpellPoints(3);
            if (RandomNumberGenerator.rollSucceeds(barbedSkinProbability))
            {
                System.out.println("Stunning with barbed skin");
                damageSource.getStatusEffects().freeze(2);
            }
        }

        return damage;
    }

    public void heal(int HP)
    {
        int bonusHPPercent = 5 * this.getStatPoints(MainCharacter.STAT_LIFE_BOND);
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
