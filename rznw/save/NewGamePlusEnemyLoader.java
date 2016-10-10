package rznw.save;

import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;

import java.io.BufferedReader;

public class NewGamePlusEnemyLoader extends ComponentLoader
{
    public void load(GameWorld gameWorld, BufferedReader fileReader)
    {
        int numEnemies = this.readInteger(fileReader);

        gameWorld.clearEnemySet();

        for (int i = 0; i < numEnemies; i++)
        {
            int enemyIndex = this.readInteger(fileReader);
            int level = this.readInteger(fileReader);
            int hp = this.readInteger(fileReader);
            int mp = this.readInteger(fileReader);

            for (int j = 0; j < SimpleStatusEffects.NUM_STATUS_EFFECTS; j++)
            {
                this.readInteger(fileReader);
            }

            for (int j = 0; j < TurnBasedStatusEffects.NUM_STATUS_EFFECTS; j++)
            {
                this.readInteger(fileReader);
            }

            for (int j = 0; j < StatusEffectStats.NUM_STATS; j++)
            {
                this.readInteger(fileReader);
            }
        }
    }
}
