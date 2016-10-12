package rznw.turn;

import rznw.game.SummonedCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.turn.positionchange.EnemyAIBasedPositionChange;

import java.util.Collection;
import java.util.Iterator;

public class SummonTurnHandler implements TurnFragmentHandler
{
    private GameWorld gameWorld;

    public SummonTurnHandler(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public void handleTurnFragment()
    {
        System.out.println("Handling summon turns");

        MainCharacter character = this.gameWorld.getMainCharacter();

        Collection<SummonedCharacter> summons = this.gameWorld.getMap().getSummons();
        for (Iterator iterator = summons.iterator(); iterator.hasNext();)
        {
            System.out.println("Have a summon");

            SummonedCharacter summon = (SummonedCharacter)iterator.next();
            EnemyAIBasedPositionChange summonPositionChange = summon.getPositionChange(this.gameWorld);
            System.out.println("Position change: " + summonPositionChange.getInitialRow() + ", " + summonPositionChange.getInitialColumn() + " -> " + summonPositionChange.getFinalRow() + ", " + summonPositionChange.getFinalColumn());

            new CharacterTurnHandler(this.gameWorld).handleTurnFragment(summonPositionChange, summon);

            summon.getStatusEffects().processTurn(this.gameWorld);
        }
    }
}
