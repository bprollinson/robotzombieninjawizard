package rznw.game;

import rznw.game.Robot;
import rznw.game.Zombie;
import rznw.game.Ninja;
import rznw.game.Wizard;

import java.util.Random;

public class CharacterGenerator
{
    public static GameCharacter generate()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(4);

        switch (randomNumber)
        {
            case 0:
                return new Robot();
            case 1:
                return new Zombie();
            case 2:
                return new Ninja();
            default:
                return new Wizard();
        }
    }
}
