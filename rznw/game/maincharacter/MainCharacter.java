package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.maincharacter.inventory.Inventory;

public abstract class MainCharacter extends Character
{
    private Inventory inventory;

    public MainCharacter()
    {
        this.inventory = new Inventory();
    }

    public int getMaxHP()
    {
        return 1000;
    }

    public int getDamage()
    {
        return 5;
    }

    public Inventory getInventory()
    {
        return this.inventory;
    }
}
