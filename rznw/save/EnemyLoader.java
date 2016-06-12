package rznw.save;

import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class EnemyLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numEnemies = this.readInteger(fileReader);
        System.out.println("Num enemies; " + numEnemies);

        for (int i = 0; i < numEnemies; i++)
        {
             String enemyClass = this.readLine(fileReader);
             int hp = this.readInteger(fileReader);
             int mp = this.readInteger(fileReader);

             System.out.println("Enemy stats: " + enemyClass + " - " + hp + " - " + mp);
        }
    }
}
