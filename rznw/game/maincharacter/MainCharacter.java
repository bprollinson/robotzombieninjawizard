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

    public static final int STAT_HEALTH = 0;
    public static final int STAT_MANA = 12;

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

    public void addStatPoint(int statNumber)
    {
        this.stats[statNumber]++;
    }

    public int getStatPoints(int statNumber)
    {
        return this.stats[statNumber];
    }

    public void addSkillPoint(int skillNumber)
    {
        this.skills[skillNumber]++;
    }

    public int getSkillPoints(int skillNumber)
    {
        return this.skills[skillNumber];
    }

    public void addSpellPoint(int spellNumber)
    {
        this.spells[spellNumber]++;
    }

    public int getSpellPoints(int spellNumber)
    {
        int spellPoints = this.spells[spellNumber];

        int bonusSpellPoints = 0;

        if (this.getStatusEffects().magicSeedsEnabled())
        {
            int magicSeedPoints = this.getSkillPoints(12);
            bonusSpellPoints = (int)Math.floor(magicSeedPoints / 4);

            System.out.println("Bonus spell points: " + bonusSpellPoints);
            spellPoints += bonusSpellPoints;
        }

        return spellPoints;
    }

    public void resetStateAfterLevelUp()
    {
        this.fillHP();
        this.fillMP();
    }

    public abstract String getCharacterClass();

    public void useItem(int itemIndex, GameWorld gameWorld)
    {
        InventoryItem item = this.inventory.getItemGroup(itemIndex).getItem();
        item.useOnCharacter(this, gameWorld);
        this.inventory.removeItems(new InventoryItemGroup(item, 1));
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
        int toHitPercent = 50 + 2 * this.getStatPoints(4);

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
        int toDodgePercent = 2 *  this.getStatPoints(5);

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
            int dodgePenalty = Math.max(21 - this.getSkillPoints(9), 1);
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
        return Math.max(1, 20 - this.getStatPoints(1));
    }

    private int getStepsForMPHeal()
    {
        return Math.max(1, 20 - this.getStatPoints(13));
    }

    private int getStepsForManaRiver()
    {
        return Math.max(1, 20 - this.getSkillPoints(15));
    }

    public void incrementSteps()
    {
        if (this.getStatPoints(1) > 0)
        {
            this.HPSteps++;
            if (this.HPSteps >= this.getStepsForHeal())
            {
                this.heal(10);
                this.HPSteps = 0;
            }
        }

        if (this.getStatPoints(13) > 0)
        {
            this.MPSteps++;
            if (this.MPSteps >= this.getStepsForMPHeal())
            {
                this.healMP(10);
                this.MPSteps = 0;
            }
        }

        if (this.getSkillPoints(15) > 0)
        {
            this.manaRiverSteps++;
            if (this.manaRiverSteps >= this.getStepsForManaRiver())
            {
                int manaRiverProbability = this.getSkillPoints(15);

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

        return 2 + this.getStatPoints(6) + equipmentBonus;
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
            int MPFromDamage = (int)Math.floor(5.0 / 100.0 * this.getSkillPoints(13) * damage);
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
        int bonusHPPercent = 5 * this.getStatPoints(3);
        int bonusHP = (int)Math.floor(bonusHPPercent / 100.0 * HP);

        if (bonusHP > 0)
        {
            System.out.println("Bonus HP: " + bonusHP);
        }

        super.heal(HP + bonusHP);
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
