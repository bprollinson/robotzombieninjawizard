package rznw.turn;

import rznw.game.Character;
import rznw.game.StatusEffects;
import rznw.game.SummonedZombie;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.RandomInventoryGenerator;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedZombieMapElement;

public class EnemyClearer
{
    private KillBonusGranter killBonusGranter;

    public EnemyClearer()
    {
        this.killBonusGranter = new KillBonusGranter();
    }

    public void clearEnemies(GameWorld gameWorld, Map map, MainCharacter character)
    {
        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);
                if (element != null && element.isEnemy())
                {
                    Character enemy = ((EnemyMapElement)element).getCharacter();
                    if (enemy.isDead())
                    {
                        this.killBonusGranter.grantKillBonuses(character, enemy);
                        map.setElement(element.getRow(), element.getColumn(), null);

                        if (character.getStatusEffects().isInferringZombie())
                        {
                            character.getStatusEffects().setStatusEffect(StatusEffects.EFFECT_INFER_ZOMBIE, false);

                            System.out.println("Inferring zombie at: " + element.getRow() + ", " + element.getColumn());

                            int maxHP = 200 + 10 * character.getSpellPoints(13);
                            System.out.println("Max HP is: " + maxHP);

                            SummonedZombie zombie = new SummonedZombie(maxHP);
                            SummonedZombieMapElement zombieElement = new SummonedZombieMapElement(element.getRow(), element.getColumn(), zombie);
                            zombie.setMapElement(zombieElement);
                            gameWorld.getMap().setElement(zombieElement.getRow(), zombieElement.getColumn(), zombieElement);
                        }

                        if (enemy.isFinalBoss())
                        {
                            gameWorld.flagGameCompleted();
                            character.getStatusEffects().setStatusEffect(StatusEffects.EFFECT_REGENERATE_SHOP, true);
                            RandomInventoryGenerator.handleRegeneration(gameWorld);
                        }
                    }
                }
            }
        }
    }
}
