package rznw.game;

import rznw.game.statuseffects.StatusEffects;
import rznw.map.GameWorld;
import rznw.map.element.CharacterMapElement;

public abstract class Character
{
    public static int DAMAGE_SOURCE_OTHER = 0;
    public static int DAMAGE_SOURCE_PHYSICAL = 1;
    public static int DAMAGE_SOURCE_MAGICAL = 2;

    protected int HP;
    protected int MP;
    protected StatusEffects statusEffects;
    protected CharacterMapElement mapElement;

    public Character()
    {
        this.HP = this.getMaxHP();
        this.MP = this.getMaxMP();
        this.statusEffects = new StatusEffects(this);
    }

    public Character(int HP, int MP)
    {
        this.HP = HP;
        this.MP = MP;
        this.statusEffects = new StatusEffects(this);
    }

    public CharacterMapElement getMapElement()
    {
        return this.mapElement;
    }

    public void setMapElement(CharacterMapElement mapElement)
    {
        this.mapElement = mapElement;
    }

    public abstract void generateMapElement(int row, int column);

    public int heal(int HP)
    {
        this.HP = Math.min(this.HP + HP, this.getMaxHP());

        return HP;
    }

    public void healMP(int MP)
    {
        this.MP = Math.min(this.MP + MP, this.getMaxMP());
    }

    public int damage(int damage, Character damageSource, GameWorld gameWorld, int damageSourceType)
    {
        this.HP -= damage;

        return damage;
    }

    public boolean isDead()
    {
        return this.HP <= 0;
    }

    public abstract int getMaxHP();

    public void setHP(int HP)
    {
        this.HP = HP;
    }

    public int getHP()
    {
        return this.HP;
    }

    public abstract int getMaxMP();

    public int getMP()
    {
        return this.MP;
    }

    public void setMP(int MP)
    {
        this.MP = MP;
    }

    protected void fillHP()
    {
        this.HP = this.getMaxHP();
    }

    protected void fillMP()
    {
        this.MP = this.getMaxMP();
    }

    public StatusEffects getStatusEffects()
    {
        return this.statusEffects;
    }

    public boolean isEnemy()
    {
        return false;
    }

    public boolean isMainCharacter()
    {
        return false;
    }

    public boolean isSummon()
    {
        return false;
    }

    public boolean isFinalBoss()
    {
        return false;
    }

    public abstract int getDamage();

    public abstract boolean meleeAttackHits();

    public abstract boolean dodgesAttack();

    public abstract String getLogName();
}
