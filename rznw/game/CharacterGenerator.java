package rznw.game;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.Werewolf;
import rznw.game.enemy.Mummy;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.Robot;
import rznw.game.maincharacter.Zombie;
import rznw.game.maincharacter.Ninja;
import rznw.game.maincharacter.Wizard;

import java.util.Random;

public class CharacterGenerator
{
    private static final int CLASS_ROBOT = 0;
    private static final int CLASS_ZOMBIE = 1;
    private static final int CLASS_NINJA = 2;
    private static final int CLASS_WIZARD = 3;

    public MainCharacter generateMainCharacter()
    {
        MainCharacter[] mainCharacterList = new MainCharacter[]
        {
            new Robot(),
            new Zombie(),
            new Ninja(),
            new Wizard()
        };

        Random random = new Random();
        int randomNumber = random.nextInt(mainCharacterList.length);

        return mainCharacterList[randomNumber];
    }

    public MainCharacter generateMainCharacter(int characterClass)
    {
        switch (characterClass)
        {
            case CharacterGenerator.CLASS_ROBOT:
                return new Robot();
            case CharacterGenerator.CLASS_ZOMBIE:
                return new Zombie();
            case CharacterGenerator.CLASS_NINJA:
                return new Ninja();
            case CharacterGenerator.CLASS_WIZARD:
                return new Wizard();
        }

        return null;
    }

    public EnemyCharacter generateEnemy()
    {
        EnemyCharacter[] enemyList = new EnemyCharacter[]
        {
            new Werewolf(),
            new Mummy()
        };

        Random random = new Random();
        int randomNumber = random.nextInt(enemyList.length);

        return enemyList[randomNumber];
    } 
}
