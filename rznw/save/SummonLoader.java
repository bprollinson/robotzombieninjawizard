package rznw.save;

import rznw.game.SummonedCharacter;
import rznw.game.SummonedGolem;
import rznw.game.SummonedZombie;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class SummonLoader extends ComponentLoader
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

            SummonedCharacter summon = null;
            switch (summonNumber)
            {
                case SummonedGolem.SUMMON_NUMBER:
                    summon = new SummonedGolem(maxHP);
                    break;
                case SummonedZombie.SUMMON_NUMBER:
                    summon = new SummonedZombie(maxHP);
                    break;
            }

            summon.setHP(hp);
            gameWorld.addSummonToSet(summon);
        }
    }
}
