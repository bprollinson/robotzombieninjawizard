package rznw.game.maincharacter.inventory;

import rznw.utility.RandomNumberGenerator;

public class RandomWeaponGenerator
{
    public Weapon generateRandomWeapon()
    {
        int index = RandomNumberGenerator.randomInteger(0, 13);

        switch (index)
        {
            case 0:
                return new BloodSword();
            case 1:
                return new CrusherHammer();
            case 2:
                return new DeathScythe();
            case 3:
                return new GravityBlade();
            case 4:
                return new GauntletOfDarkness();
            case 5:
                return new IceRod();
            case 6:
                return new InvisibilityWand();
            case 7:
                return new MagicJavelin();
            case 8:
                return new QuicksandHammer();
            case 9:
                return new RiddleWand();
            case 10:
                return new ThiefGlove();
            case 11:
                return new ViperDagger();
            case 12:
                return new WandOfSummoning();
            case 13:
                return new WoodenSword();
            default:
                return null;
        }
    }
}
