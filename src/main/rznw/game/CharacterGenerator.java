package rznw.game;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.EnemyCharacterGenerator;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.MainCharacterGenerator;

public class CharacterGenerator
{
    public MainCharacter generateMainCharacter(int characterClass)
    {
        return new MainCharacterGenerator().generateCharacter(characterClass);
    }

    public EnemyCharacter generateEnemy(int dungeonLevel)
    {
        return new EnemyCharacterGenerator().generateCharacter(dungeonLevel);
    }

    public EnemyCharacter generateEndBoss()
    {
        return new EnemyCharacterGenerator().generateEndBoss();
    }
}
