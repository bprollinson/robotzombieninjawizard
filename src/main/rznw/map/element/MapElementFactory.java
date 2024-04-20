package rznw.map.element;

import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.TurnBasedStatusEffects;
import rznw.map.GameWorld;

public class MapElementFactory
{
    public static MapElement factory(GameWorld gameWorld, int elementIndex, int row, int column, String metadata)
    {
        switch (elementIndex)
        {
            case Blotch.ELEMENT_NUMBER:
                return new Blotch(row, column);
            case DisappearingEnemyMapElement.ELEMENT_NUMBER:
                int invisibleEnemyIndex = gameWorld.getEnemyIndex();
                EnemyCharacter invisibleEnemy = gameWorld.getEnemy(invisibleEnemyIndex);
                gameWorld.incrementEnemyIndex();

                invisibleEnemy.generateMapElement(row, column);
                invisibleEnemy.getStatusEffects().setStatusEffectTurns(TurnBasedStatusEffects.EFFECT_INVISIBLE, Integer.parseInt(metadata));
                return invisibleEnemy.getMapElement();
            case EnemyMapElement.ELEMENT_NUMBER:
                int enemyIndex = gameWorld.getEnemyIndex();
                EnemyCharacter enemy = gameWorld.getEnemy(enemyIndex);
                gameWorld.incrementEnemyIndex();

                enemy.generateMapElement(row, column);
                return enemy.getMapElement();
            case FireElement.ELEMENT_NUMBER:
                MapElement previousElement = null;
                String[] parts = metadata.split("/", -1);
                if (parts.length >= 3)
                {
                    previousElement = MapElementFactory.factory(gameWorld, Integer.parseInt(parts[1]), row, column, parts[2]);
                }

                return new FireElement(row, column, previousElement, Integer.parseInt(parts[0]));
            case MainCharacterMapElement.ELEMENT_NUMBER:
                MainCharacter mainCharacter = gameWorld.getMainCharacter();
                mainCharacter.generateMapElement(row, column);

                return mainCharacter.getMapElement();
            case Path.ELEMENT_NUMBER:
                return new Path(row, column);
            case Projectile.ELEMENT_NUMBER:
                return new Projectile(row, column);
            case RockWall.ELEMENT_NUMBER:
                return new RockWall(row, column, Integer.parseInt(metadata));
            case Stairs.ELEMENT_NUMBER:
                return new Stairs(row, column);
            case SummonedGolemMapElement.ELEMENT_NUMBER:
                int summonIndex = gameWorld.getSummonIndex();
                SummonedCharacter summon = gameWorld.getSummon(summonIndex);
                gameWorld.incrementSummonIndex();

                CharacterMapElement element = new SummonedGolemMapElement(row, column, summon);
                summon.setMapElement(element);

                return element;
            case SummonedZombieMapElement.ELEMENT_NUMBER:
                int zombieIndex = gameWorld.getSummonIndex();
                SummonedCharacter zombie = gameWorld.getSummon(zombieIndex);
                gameWorld.incrementSummonIndex();

                CharacterMapElement zombieElement = new SummonedZombieMapElement(row, column, zombie);
                zombie.setMapElement(zombieElement);

                return zombieElement;
            case TrapMapElement.ELEMENT_NUMBER:
                String[] metadataParts = metadata.split(",");
                boolean isSprung = metadataParts[0].equals("1");
                boolean isFound = metadataParts[1].equals("1");

                TrapMapElement trapElement = new TrapMapElement(row, column);
                if (isSprung)
                {
                    trapElement.spring();
                }

                if (isFound)
                {
                    trapElement.find();
                }

                return trapElement;
            case Void.ELEMENT_NUMBER:
                return new Void(row, column);
            case Wall.ELEMENT_NUMBER:
                return new Wall(row, column);
            case Waypoint.ELEMENT_NUMBER:
                return new Waypoint(row, column);
        }

        return null;
    }
}
