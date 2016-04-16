package rznw.game;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.Assassin;
import rznw.game.enemy.BeastSummoner;
import rznw.game.enemy.Crusher;
import rznw.game.enemy.Dragon;
import rznw.game.enemy.Enchanter;
import rznw.game.enemy.HealthNinja;
import rznw.game.enemy.InvisibleWizard;
import rznw.game.enemy.Javelineer;
import rznw.game.enemy.Leech;
import rznw.game.enemy.Mummy;
import rznw.game.enemy.Nosferatu;
import rznw.game.enemy.Oni;
import rznw.game.enemy.Phantasm;
import rznw.game.enemy.RockMan;
import rznw.game.enemy.Sphinx;
import rznw.game.enemy.Thief;
import rznw.game.enemy.Undertaker;
import rznw.game.enemy.Viper;
import rznw.game.enemy.Werewolf;
import rznw.game.enemy.XRayCat;
import rznw.game.enemy.Yeti;
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
            {4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 100},
            {4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 100}
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
            case 5:
                return new Yeti(enemyLevel);
            case 6:
                return new Enchanter(enemyLevel);
            case 7:
                return new Nosferatu(enemyLevel);
            case 8:
                return new RockMan(enemyLevel);
            case 9:
                return new HealthNinja(enemyLevel);
            case 10:
                return new Leech(enemyLevel);
            case 11:
                return new Thief(enemyLevel);
            case 12:
                return new InvisibleWizard(enemyLevel);
            case 13:
                return new Dragon(enemyLevel);
            case 14:
                return new XRayCat(enemyLevel);
            case 15:
                return new BeastSummoner(enemyLevel);
            case 16:
                return new Undertaker(enemyLevel);
            case 17:
                return new Phantasm(enemyLevel);
            case 18:
                return new Crusher(enemyLevel);
            case 19:
                return new Oni(enemyLevel);
            case 20:
                return new Javelineer(enemyLevel);
        }

        return null;
    } 
}
