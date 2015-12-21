package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.inventory.Equipment;
import rznw.game.maincharacter.inventory.Inventory;
import rznw.game.maincharacter.inventory.InventoryItem;
import rznw.game.maincharacter.inventory.InventoryItemGroup;
import rznw.game.maincharacter.inventory.Shield;
import rznw.game.maincharacter.inventory.Weapon;
import rznw.game.skill.SkillFactory;
import rznw.game.spell.SpellFactory;
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

    private static String[] statName = {
        "Health",
        "Physical Regeneration",
        "Last Breath",
        "Life Bond",
        "Accuracy",
        "Dodge",
        "Sight",
        "Find Traps",
        "Damage",
        "Padding",
        "Unencumberance",
        "Thick Skin",
        "Mana",
        "Mental Regeneration",
        "Mana Burn",
        "Magic Resistance"
    };

    private static String[] statDescription = {
        "Increases your maximum HP.",
        "Increases the amount of HP you recover while exploring the dungeon.",
        "Increases your chance to recover with 1 HP when dying.",
        "Amplifies the strength of healing effects on you.",
        "Affects your probability of hitting enemies in melee combat.",
        "Affects your probability of dodging attacks.",
        "Increases your field of view.",
        "Increases your ability of finding hidden traps.",
        "Increases your damage in melee combat.",
        "Reduces the damage you take.",
        "Increases the amount you can carry in your inventory.",
        "Increases your probability of dodging status effects.",
        "Increases your maximum MP.",
        "Increases the amount of MP you recover while exploring the dungeon.",
        "Increases the damage you deal with your spells.",
        "Decreases the damage your opponents' spells do to you."
    };

    private static String[] skillCategory = {
        "Vitality",
        "Agility",
        "Fortitude",
        "Magic"
    };

    private static String[] skillName = {
        "Detect Vitality",
        "Detect Enemies",
        "Blood Rage",
        "Potion Find",
        "Summon Shopkeeper",
        "Waypoint",
        "Fast Hands",
        "Find Stairs",
        "Item Trade",
        "Rage",
        "Abundance",
        "Disarm Traps",
        "Magic Seeds",
        "Mana Suck",
        "Protective Field",
        "Mana River"
    };

    private static String[] skillDescription = {
        "Detects enemy HP.",
        "Detects the position of enemies.",
        "Increases the damage you deal when injured.",
        "Increases your chance of finding potions from enemies.",
        "Summons a shopkeeper who buys and sells goods.",
        "Sets up a waypoint or allows you to return to one.",
        "Increases enemy item drop rate.",
        "Shows you the position of the nearest set of stairs.",
        "Allows you to trade in multiple items for a new random item.",
        "Increases your damage but decreases your defense for a period of time.",
        "Increases the amount of gold you receive from enemies.",
        "Has a chance to automatically disarm traps you encounter.",
        "Increases your effective spell levels.",
        "You receive MP from damage you receive.",
        "Grants you additional chance of avoiding magic damage.",
        "Provides you with a periodic chance to refill your MP."
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

        this.inventory = new Inventory();
        this.equipment = new Equipment();

        this.HP = this.getMaxHP();
        this.MP = this.getMaxMP();
    }

    public static String getStatCategory(int categoryNumber)
    {
        return MainCharacter.statCategory[categoryNumber];
    }

    public static String getStatName(int statNumber)
    {
        return MainCharacter.statName[statNumber];
    }

    public static String getStatDescription(int statNumber)
    {
        return MainCharacter.statDescription[statNumber];
    }

    public static String getSkillCategory(int categoryNumber)
    {
        return MainCharacter.skillCategory[categoryNumber];
    }

    public static String getSkillName(int skillNumber)
    {
        return MainCharacter.skillName[skillNumber];
    }

    public static String getSkillDescription(int skillNumber)
    {
        return MainCharacter.skillDescription[skillNumber];
    }

    public abstract String getSpellCategory(int categoryNumber);

    public abstract String getSpellName(int spellNumber);

    public abstract String getSpellDescription(int spellNumber);

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
        int damage = 50 + 5 * this.getStatPoints(8);

        Weapon weapon = this.getEquipment().getEquippedWeapon();
        if (weapon != null)
        {
            int weaponDamage = weapon.getDamage();
            System.out.println("Additional weapon damage: " + weaponDamage);
            damage += weaponDamage;
        }

        double bloodRageFactor = this.getSkillPoints(2) / 100.0 * (this.getMaxHP() - this.getHP()) / this.getMaxHP();

        if (bloodRageFactor > 0.0) {
            System.out.println("Blood rage factor: " + bloodRageFactor);
        }

        return (int)Math.floor(damage * (1 + bloodRageFactor));
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
        return this.spells[spellNumber];
    }

    public void resetStateAfterLevelUp()
    {
        this.fillHP();
        this.fillMP();
    }

    public abstract String getCharacterClass();

    public void useItem(int itemIndex)
    {
        InventoryItem item = this.inventory.getItemGroup(itemIndex).getItem();
        item.useOnCharacter(this);
        this.inventory.removeItems(new InventoryItemGroup(item, 1));
    }

    public abstract SpellFactory getSpellFactory();

    public SkillFactory getSkillFactory()
    {
        return new SkillFactory();
    }

    public boolean meleeAttackHits()
    {
        int toHitPercent = 50 + 2 * this.getStatPoints(4);

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
    }

    public int getViewRadius()
    {
        return 2 + this.getStatPoints(6);
    }

    public void damage(int damage, Character damageSource, GameWorld gameWorld, int damageSourceType)
    {
        int paddingPercent = 2 * this.getStatPoints(9);
        if (this.getStatusEffects().isResistingDamage())
        {
            paddingPercent += 2 * this.getSpellPoints(1);
        }

        Shield shield = this.getEquipment().getEquippedShield();
        if (shield != null)
        {
            int shieldPaddingPercent = shield.getPaddingPercent();
            System.out.println("Additional shield padding percent: " + shieldPaddingPercent);
            paddingPercent += shieldPaddingPercent;
        }

        int padding = (int)Math.floor(paddingPercent / 100.0 * damage);

        if (padding > 0)
        {
            System.out.println("Padding percent: " + paddingPercent);
            System.out.println("Padding damage: " + padding);
        }

        if (this.getStatusEffects().isReversingPain())
        {
            System.out.println("Reversing pain!");

            this.HP += damage - padding;
            return;
        }

        this.HP -= damage - padding;

        int MPFromDamage = (int)Math.floor(5.0 / 100.0 * this.getSkillPoints(13) * (damage - padding));
        if (MPFromDamage > 0)
        {
            System.out.println("Healing MP from damage: " + MPFromDamage);
            this.healMP(MPFromDamage);
        }

        if (this.getStatusEffects().isCounterstriking() && damageSource instanceof EnemyCharacter)
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

        if (this.getStatusEffects().thornSkinEnabled() && damageSource instanceof EnemyCharacter)
        {
            System.out.println("Attacking back with thorn skin");

            int thornSkinDamage = 5 * this.getSpellPoints(2);
            System.out.println("Enemy hp before: " + damageSource.getHP());
            damageSource.damage(thornSkinDamage, this, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
            System.out.println("Enemy hp after: " + damageSource.getHP());
        }

        if (this.getStatusEffects().poisonSkinEnabled() && damageSource instanceof EnemyCharacter)
        {
            System.out.println("Poisoning with poison skin");

            damageSource.getStatusEffects().poison();
        }

        if (this.getStatusEffects().isDeathStriking() && damageSource instanceof EnemyCharacter)
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

        if (this.getStatusEffects().smokeBombEnabled() && damageSource instanceof EnemyCharacter)
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

        if (this.getStatusEffects().barbedSkinEnabled() && damageSource instanceof EnemyCharacter)
        {
            System.out.println("Checking barbed skin");

            int barbedSkinProbability = 5 * this.getSpellPoints(3);
            if (RandomNumberGenerator.rollSucceeds(barbedSkinProbability))
            {
                System.out.println("Stunning with barbed skin");
                damageSource.getStatusEffects().freeze(2);
            }
        }
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
}
