package rznw.turn;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.stat.Stat;
import rznw.map.GameWorld;
import rznw.map.Map;
import rznw.map.element.MapElement;
import rznw.ui.LogRendererFactory;
import rznw.utility.RandomNumberGenerator;

public class PostEnemyTurnHandler implements TurnFragmentHandler
{
    private GameWorld gameWorld;

    public PostEnemyTurnHandler(GameWorld gameWorld)
    {
        this.gameWorld = gameWorld;
    }

    public void handleTurnFragment()
    {
        Map map = this.gameWorld.getMap();
        MainCharacter character = this.gameWorld.getMainCharacter();

        if (character.isDead())
        {
            this.handleMainCharacterRevival();
        }

        new EnemyClearer().clearEnemies(this.gameWorld, map, character);

        for (int row = 0; row < Map.NUM_ROWS; row++)
        {
            for (int column = 0; column < Map.NUM_COLUMNS; column++)
            {
                MapElement element = map.getElement(row, column);
                if (element != null)
                {
                    element.processTurn(map);
                }

                element = map.getBackgroundElement(row, column);
                if (element != null)
                {
                    element.processTurn(map);
                }
            }
        }

        character.getStatusEffects().processTurn(this.gameWorld);
    }

    private void handleMainCharacterRevival()
    {
        MainCharacter character = this.gameWorld.getMainCharacter();
        int revivalProbability = 5 * character.getStats().getStatPoints(Stat.STAT_LAST_BREATH);

        if (RandomNumberGenerator.rollSucceeds(revivalProbability))
        {
            character.setHP(1);
            LogRendererFactory.instance().log("You revived with 1 HP via last breath.");
        }
    }
}
