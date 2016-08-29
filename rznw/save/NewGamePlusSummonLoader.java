package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class NewGamePlusSummonLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numSummons = this.readInteger(fileReader);
        System.out.println("Num summons: " + numSummons);

        gameWorld.clearSummonSet();

        for (int i = 0; i < numSummons; i++)
        {
            int summonNumber = this.readInteger(fileReader);
            int maxHP = this.readInteger(fileReader);
            int hp = this.readInteger(fileReader);

            System.out.println("Summon info: " + summonNumber + " - " + maxHP + " - " + hp);
        }
    }
}
