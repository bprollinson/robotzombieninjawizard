package rznw.game.spell.zombie;

import rznw.game.SummonedCharacter;
import rznw.game.SummonedZombie;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.spell.UndirectedSpell;
import rznw.map.ClosestSquareCalculator;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.map.element.SummonedZombieMapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

import java.util.Collection;
import java.util.Iterator;

public class MultiplyZombiesSpell extends UndirectedSpell
{
    public String getDisplayName()
    {
        return "Multiply Zombies";
    }

    public String getDescription()
    {
        return "Each zombie you control has a chance to multiply, creating an additional zombie.";
    }

    public void cast(GameWorld gameWorld, int spellPoints)
    {
        LogRendererFactory.instance().log("Casting multiply zombies.");

        Map map = gameWorld.getMap();

        int multiplyProbability = 5 * spellPoints;
        int maxHP = 100 + 5 * gameWorld.getMainCharacter().getSpells().getSpellPoints(ZombieSpellFactory.SPELL_SUMMON_ZOMBIE);

        Collection<SummonedCharacter> summons = gameWorld.getMap().getSummons();
        for (Iterator iterator = summons.iterator(); iterator.hasNext();)
        {
            SummonedCharacter summon = (SummonedCharacter)iterator.next();

            if (RandomNumberGenerator.rollSucceeds(multiplyProbability))
            {
                MapElement element = this.getPositionElement(gameWorld, summon.getMapElement());

                SummonedZombie zombie = new SummonedZombie(maxHP);
                SummonedZombieMapElement zombieElement = new SummonedZombieMapElement(element.getRow(), element.getColumn(), zombie);
                zombie.setMapElement(zombieElement);
                gameWorld.getMap().setElement(zombieElement.getRow(), zombieElement.getColumn(), zombieElement);
                LogRendererFactory.instance().log("Summoned a zombie.");
            }
        }
    }

    public int getMPCost(MainCharacter character, int spellPoints)
    {
        return Math.max(200 - 10 * spellPoints, 1);
    }

    private MapElement getPositionElement(GameWorld gameWorld, MapElement startingPosition)
    {
        return new ClosestSquareCalculator(gameWorld).getClosestPositionElement(startingPosition);
    }

    public String[] getStats(MainCharacter character, int spellPoints)
    {
        int summonSpellPoints = character.getSpells().getSpellPoints(ZombieSpellFactory.SPELL_SUMMON_ZOMBIE);

        return new String[] {
            "MP cost: " + this.getMPCost(character, spellPoints),
            "Chance to multiply: " + 5 * spellPoints + "%",
            "Zombie HP: " + (100 + 5 * summonSpellPoints),
            "Zombie damage: 10"
        };
    }
}
