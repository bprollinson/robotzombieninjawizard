package rznw.map.element;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;

public class FireElement extends MapElement
{
    private MapElement previousMapElement;
    private int duration;

    public FireElement(int row, int column, MapElement previousMapElement, int duration)
    {
        super(row, column);

        this.previousMapElement = previousMapElement;
        this.duration = duration;
    }

    public void decreaseDuration()
    {
        this.duration--;
    }

    public boolean isExpired()
    {
        return this.duration <= 0;
    }

    public MapElement getPreviousMapElement()
    {
        return this.previousMapElement;
    }

    public char getDisplayCharacter()
    {
        return 'F';
    }

    public void collideWithEnemy(GameWorld gameWorld, EnemyCharacter enemyCharacter)
    {
        MainCharacter character = gameWorld.getMainCharacter();

        System.out.println("Enemy runs into fire element");
        int damage = 10 * character.getSpellPoints(4);
        System.out.println("Enemy HP before: " + enemyCharacter.getHP());
        enemyCharacter.damage(damage, character, gameWorld, Character.DAMAGE_SOURCE_MAGICAL);
        System.out.println("Enemy HP after: " + enemyCharacter.getHP());
    }
}
