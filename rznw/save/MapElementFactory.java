package rznw.save;

import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.map.GameWorld;
import rznw.map.element.Blotch;
import rznw.map.element.DisappearingEnemyMapElement;
import rznw.map.element.EnemyMapElement;
import rznw.map.element.FireElement;
import rznw.map.element.MainCharacterMapElement;
import rznw.map.element.MapElement;
import rznw.map.element.Path;
import rznw.map.element.Projectile;
import rznw.map.element.RockWall;
import rznw.map.element.Stairs;
import rznw.map.element.SummonedGolemMapElement;
import rznw.map.element.SummonedZombieMapElement;
import rznw.map.element.TrapMapElement;
import rznw.map.element.Void;
import rznw.map.element.Wall;
import rznw.map.element.Waypoint;

public class MapElementFactory
{
    public static MapElement factory(GameWorld gameWorld, int elementIndex, int row, int column, String metadata)
    {
        switch (elementIndex)
        {
            case Blotch.ELEMENT_NUMBER:
                return new Blotch(row, column);
            case DisappearingEnemyMapElement.ELEMENT_NUMBER:
                break;
            case EnemyMapElement.ELEMENT_NUMBER:
                int enemyIndex = gameWorld.getEnemyIndex();
                EnemyCharacter enemy = gameWorld.getEnemy(enemyIndex);
                gameWorld.incrementEnemyIndex();

                enemy.generateMapElement(row, column);
                return enemy.getMapElement();
            case FireElement.ELEMENT_NUMBER:
                break;
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
                break;
            case SummonedZombieMapElement.ELEMENT_NUMBER:
                break;
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
