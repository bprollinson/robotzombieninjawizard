package rznw.map.element;

public class RockWall extends MapElement
{
    public static final int ELEMENT_NUMBER = 8;

    private int HP;

    public RockWall(int row, int column, int HP)
    {
        super(row, column);

        this.HP = HP;
    }

    public char getDisplayCharacter()
    {
        return '=';
    }

    public void damage(int damage)
    {
        this.HP -= damage;
    }

    public int getHP()
    {
        return this.HP;
    }

    public int getElementNumber()
    {
        return RockWall.ELEMENT_NUMBER;
    }
}
