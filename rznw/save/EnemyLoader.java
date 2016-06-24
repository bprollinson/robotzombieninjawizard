package rznw.save;

import rznw.game.enemy.EnemyCharacter;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class EnemyLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numEnemies = this.readInteger(fileReader);
        System.out.println("Num enemies; " + numEnemies);

        gameWorld.clearEnemySet();

        for (int i = 0; i < numEnemies; i++)
        {
             int enemyIndex = this.readInteger(fileReader);
             int level = this.readInteger(fileReader);
             int hp = this.readInteger(fileReader);
             int mp = this.readInteger(fileReader);

             System.out.println("Enemy stats: " + enemyIndex + " - " + level + " - " + hp + " - " + mp);

             EnemyCharacter enemy = EnemyCharacterFactory.factory(enemyIndex, level);
             gameWorld.addEnemyToSet(enemy);
        }
    }
}
