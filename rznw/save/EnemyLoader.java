package rznw.save;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.game.statuseffects.StatusEffectStats;
import rznw.game.statuseffects.TurnBasedStatusEffects;
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
            enemy.setHP(hp);
            enemy.setMP(mp);

            for (int j = 0; j < SimpleStatusEffects.NUM_STATUS_EFFECTS; j++)
            {
                enemy.getStatusEffects().setStatusEffect(j, this.readInteger(fileReader) == 1);
            }

            for (int j = 0; j < TurnBasedStatusEffects.NUM_STATUS_EFFECTS; j++)
            {
                enemy.getStatusEffects().setStatusEffectTurns(j, this.readInteger(fileReader));
            }

            for (int j = 0; j < StatusEffectStats.NUM_STATS; j++)
            {
                enemy.getStatusEffects().setStat(j, this.readInteger(fileReader));
            }

            gameWorld.addEnemyToSet(enemy);
        }
    }
}
