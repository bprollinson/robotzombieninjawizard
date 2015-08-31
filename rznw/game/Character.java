package rznw.game;

import rznw.map.element.MapElement;

public abstract class Character
{
    private int HP;
    protected MapElement mapElement;

    public Character()
    {
        this.HP = this.getMaxHP();
    }

    public MapElement getMapElement()
    {
        return this.mapElement;
    }

    public abstract void generateMapElement(int row, int column);

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

    public abstract int getDamage();
}
