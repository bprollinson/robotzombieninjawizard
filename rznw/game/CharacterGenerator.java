package rznw.game;

import rznw.game.Robot;
import rznw.game.Zombie;
import rznw.game.Ninja;
import rznw.game.Wizard;
import rznw.game.Werewolf;
import rznw.game.Mummy;

import java.util.Random;

public class CharacterGenerator
{
    private MainCharacter[] mainCharacterList;
    private EnemyCharacter[] enemyList;

    public CharacterGenerator()
    {
        this.mainCharacterList = new MainCharacter[]
        {
            new Robot(),
            new Zombie(),
            new Ninja(),
            new Wizard()
        };

        this.enemyList = new EnemyCharacter[]
        {
            new Werewolf(),
            new Mummy()
        };
    }

    public MainCharacter generateMainCharacter()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(this.mainCharacterList.length);

        return this.mainCharacterList[randomNumber];
    }

    public EnemyCharacter generateEnemy()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(this.enemyList.length);

        return this.enemyList[randomNumber];
    } 
}
