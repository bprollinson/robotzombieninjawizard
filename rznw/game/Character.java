package rznw.game;

import rznw.map.element.MapElement;

public abstract class Character
{
    protected int HP;
    protected int MP;
    protected MapElement mapElement;

    public Character()
    {
        this.HP = this.getMaxHP();
        this.MP = this.getMaxMP();
    }

    public Character(int HP, int MP)
    {
        this.HP = HP;
        this.MP = MP;
    }

    public MapElement getMapElement()
    {
        return this.mapElement;
    }

    public abstract void generateMapElement(int row, int column);

    public void heal(int HP)
    {
        this.HP = Math.min(this.HP + HP, this.getMaxHP());
    }

    public void damage(int damage)
    {
        this.HP -= damage;
    }

    public boolean isDead()
    {
        return this.HP <= 0;
    }

    public abstract int getMaxHP();

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

    public abstract int getDamage();

    public abstract boolean meleeAttackHits();
}
