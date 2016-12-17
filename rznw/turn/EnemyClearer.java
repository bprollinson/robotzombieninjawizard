package rznw.turn;

import rznw.game.Character;
import rznw.game.SummonedZombie;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.inventory.RandomInventoryGenerator;
import rznw.game.maincharacter.KillBonusGranter;
import rznw.game.spell.zombie.ZombieSpellFactory;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedMinionMapElement;
import rznw.map.element.SummonedZombieMapElement;
import rznw.ui.LogRendererFactory;

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

                        if (character.getStatusEffects().getStatusEffect(SimpleStatusEffects.EFFECT_INFER_ZOMBIE))
                        {
                            character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_INFER_ZOMBIE, false);

                            LogRendererFactory.instance().log("Inferring zombie.");

                            int maxHP = 200 + 10 * character.getSpells().getSpellPoints(ZombieSpellFactory.SPELL_INFER_ZOMBIE);

                            SummonedZombie zombie = new SummonedZombie(maxHP);
                            SummonedZombieMapElement zombieElement = new SummonedZombieMapElement(element.getRow(), element.getColumn(), zombie);
                            zombie.setMapElement(zombieElement);
                            gameWorld.getMap().setElement(zombieElement.getRow(), zombieElement.getColumn(), zombieElement);
                        }

                        if (enemy.isFinalBoss())
                        {
                            gameWorld.flagGameCompleted();
                            character.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_REGENERATE_SHOP, true);
                            RandomInventoryGenerator.handleRegeneration(gameWorld);
                        }
                    }
                } else if (element != null && element instanceof SummonedMinionMapElement) {
                    Character summon = ((SummonedMinionMapElement)element).getCharacter();
                    if (summon.isDead())
                    {
                        map.setElement(element.getRow(), element.getColumn(), null);
                    }
                }
            }
        }
    }
}
