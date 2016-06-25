package rznw.save;

import rznw.map.GameWorld;

import java.io.BufferedReader;

public class SummonLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numSummons = this.readInteger(fileReader);
        System.out.println("Num summons: " + numSummons);
    }
}
