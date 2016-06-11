package rznw.game.maincharacter;

public class MainCharacterGenerator
{
    public static final int CLASS_ROBOT = 0;
    public static final int CLASS_ZOMBIE = 1;
    public static final int CLASS_NINJA = 2;
    public static final int CLASS_WIZARD = 3;

    public MainCharacter generateCharacter(int characterClass)
    {
        switch (characterClass)
        {
            case MainCharacterGenerator.CLASS_ROBOT:
                return new Robot();
            case MainCharacterGenerator.CLASS_ZOMBIE:
                return new Zombie();
            case MainCharacterGenerator.CLASS_NINJA:
                return new Ninja();
            case MainCharacterGenerator.CLASS_WIZARD:
                return new Wizard();
        }

        return null;
    }
}
