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
