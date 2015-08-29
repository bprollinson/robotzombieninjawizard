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
    private GameCharacter[] characterList;
    private EnemyCharacter[] enemyList;

    public CharacterGenerator()
    {
        this.characterList = new GameCharacter[]
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

    public GameCharacter generateCharacter()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(this.characterList.length);

        return this.characterList[randomNumber];
    }

    public EnemyCharacter generateEnemy()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(this.enemyList.length);

        return this.enemyList[randomNumber];
    } 
}
