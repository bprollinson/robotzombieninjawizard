package rznw.game;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.Assassin;
import rznw.game.enemy.Werewolf;
import rznw.game.enemy.Mummy;
import rznw.game.enemy.Sphinx;
import rznw.game.enemy.Viper;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.Robot;
import rznw.game.maincharacter.Zombie;
import rznw.game.maincharacter.Ninja;
import rznw.game.maincharacter.Wizard;
import rznw.utility.RandomNumberGenerator;

public class CharacterGenerator
{
    private static final int CLASS_ROBOT = 0;
    private static final int CLASS_ZOMBIE = 1;
    private static final int CLASS_NINJA = 2;
    private static final int CLASS_WIZARD = 3;

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

    public EnemyCharacter generateEnemy(int dungeonLevel)
    {
        int[][] cumulativeEnemyProbabilities = new int[][]
        {
            {20, 40, 60, 80, 100},
            {10, 20, 30, 65, 100}
        };

        int randomNumber = RandomNumberGenerator.randomInteger(1, 100);
        int levelIndex = Math.min(dungeonLevel - 1, 1);

        int index = -1;

        for (int i = 0; i < cumulativeEnemyProbabilities[levelIndex].length; i++)
        {
            if (randomNumber <= cumulativeEnemyProbabilities[levelIndex][i])
            {
                index = i;
                break;
            }
        }

        int enemyLevel = RandomNumberGenerator.randomInteger(0, 2 * dungeonLevel - 1);
        System.out.println("Generating enemy with level: " + enemyLevel);

        switch (index)
        {
            case 0:
                return new Werewolf(enemyLevel);
            case 1:
                return new Mummy(enemyLevel);
            case 2:
                return new Assassin(enemyLevel);
            case 3:
                return new Viper(enemyLevel);
            case 4:
                return new Sphinx(enemyLevel);
        }

        return null;
    } 
}
