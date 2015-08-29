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
