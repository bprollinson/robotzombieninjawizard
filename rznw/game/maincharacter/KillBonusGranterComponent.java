package rznw.game.maincharacter;

import rznw.game.enemy.EnemyCharacter;

public interface KillBonusGranterComponent
{
    public void grantKillBonuses(MainCharacter character, EnemyCharacter enemyCharacter);
}
