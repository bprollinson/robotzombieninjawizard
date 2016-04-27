package rznw.game.enemy;

import rznw.utility.RandomNumberGenerator;

public class EnemyCharacterGenerator
{
    public EnemyCharacter generateCharacter(int dungeonLevel)
    {
        int[][] cumulativeEnemyProbabilities = new int[][]
        {
            {4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88, 92, 96, 100},
            {4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88, 92, 96, 100}
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
            case 21:
                return new KingLizardWizard(enemyLevel);
            case 22:
                return new GravityWizard(enemyLevel);
            case 23:
                return new FumeBeast(enemyLevel);
            case 24:
                return new QuicksandDweller(enemyLevel);
        }

        return null;
    }
}
