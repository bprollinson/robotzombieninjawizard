package rznw.map.element;

public class RockWall extends MapElement
{
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
}
