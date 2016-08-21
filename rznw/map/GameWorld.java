package rznw.map;

import rznw.game.CharacterGenerator;
import rznw.game.SummonedCharacter;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.maincharacter.MainCharacter;
import rznw.game.maincharacter.MainCharacterGenerator;
import rznw.game.maincharacter.inventory.ShopInventory;
import rznw.map.generator.MapGenerator;

import java.util.Vector;

public class GameWorld
{
    private CharacterGenerator characterGenerator;
    private MapGenerator mapGenerator;
    private MainCharacter character;
    private Map map;
    private Vector<EnemyCharacter> enemySet;
    private Vector<SummonedCharacter> summonSet;
    private boolean gameCompleted = false;
    private int enemyIndex;
    private int summonIndex;
    private ShopInventory shopInventory;

    public GameWorld(CharacterGenerator characterGenerator, MapGenerator mapGenerator)
    {
        this.characterGenerator = characterGenerator;
        this.mapGenerator = mapGenerator;
        this.shopInventory = new ShopInventory();
    }

    public void initializeToDefaultState(int characterClass)
    {
        System.out.println("Generating dungeon level 1");
        this.character = characterGenerator.generateMainCharacter(characterClass);
        this.map = this.mapGenerator.generate(this.character, this.characterGenerator, 1);
        this.gameCompleted = false;
        this.shopInventory = new ShopInventory();
    }

    public void generateMainCharacter(int characterClass)
    {
        this.character = this.characterGenerator.generateMainCharacter(characterClass);
    }

    public void initializeFromLoad(int dungeonLevel)
    {
        this.map = new Map(dungeonLevel);
        this.gameCompleted = false;
    }

    public void generatePreviousMap()
    {
        System.out.println("Generating dungeon level " + (this.map.getLevel() - 1));
        this.map = this.mapGenerator.generate(this.character, this.characterGenerator, this.map.getLevel() - 1);
    }

    public void generateNextMap()
    {
        System.out.println("Generating dungeon level " + (this.map.getLevel() + 1));
        this.map = this.mapGenerator.generate(this.character, this.characterGenerator, this.map.getLevel() + 1);
    }

    public MainCharacter getMainCharacter()
    {
        return this.character;
    }

    public Map getMap()
    {
        return this.map;
    }

    public void flagGameCompleted()
    {
        this.gameCompleted = true;
    }

    public boolean gameCompleted()
    {
        return this.gameCompleted;
    }

    public void clearEnemySet()
    {
        this.enemySet = new Vector<EnemyCharacter>();
    }

    public void addEnemyToSet(EnemyCharacter enemy)
    {
        this.enemySet.add(enemy);
    }

    public void clearSummonSet()
    {
        this.summonSet = new Vector<SummonedCharacter>();
    }

    public void addSummonToSet(SummonedCharacter summon)
    {
        this.summonSet.add(summon);
    }

    public void initializeEnemyIndex()
    {
        this.enemyIndex = 0;
    }

    public int getEnemyIndex()
    {
        return this.enemyIndex;
    }

    public void initializeSummonIndex()
    {
        this.summonIndex = 0;
    }

    public void incrementEnemyIndex()
    {
        this.enemyIndex++;
    }

    public int getSummonIndex()
    {
        return this.summonIndex;
    }

    public void incrementSummonIndex()
    {
        this.summonIndex++;
    }

    public EnemyCharacter getEnemy(int enemyIndex)
    {
        return this.enemySet.get(enemyIndex);
    }

    public SummonedCharacter getSummon(int summonIndex)
    {
        return this.summonSet.get(summonIndex);
    }

    public ShopInventory getShopInventory()
    {
        return this.shopInventory;
    }
}
