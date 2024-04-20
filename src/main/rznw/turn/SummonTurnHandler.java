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
        MainCharacter character = this.gameWorld.getMainCharacter();

        Collection<SummonedCharacter> summons = this.gameWorld.getMap().getSummons();
        for (Iterator iterator = summons.iterator(); iterator.hasNext();)
        {
            SummonedCharacter summon = (SummonedCharacter)iterator.next();
            EnemyAIBasedPositionChange summonPositionChange = summon.getPositionChange(this.gameWorld);

            new CharacterTurnHandler(this.gameWorld).handleTurnFragment(summonPositionChange, summon);

            summon.getStatusEffects().processTurn(this.gameWorld);
        }
    }
}
