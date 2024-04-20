package rznw.game.maincharacter;

import rznw.game.Character;
import rznw.game.enemy.EnemyCharacter;

public class KillBonusGranter
{
    private KillBonusGranterComponent[] components;

    public KillBonusGranter()
    {
        this.components = new KillBonusGranterComponent[] {
            new ExperienceKillBonusGranter(),
            new GoldKillBonusGranter(),
            new ItemKillBonusGranter(),
            new EquipmentKillBonusGranter()
        };
    }

    public void grantKillBonuses(Character character, Character otherCharacter)
    {
        if (!character.isMainCharacter() || !otherCharacter.isEnemy())
        {
            return;
        }

        MainCharacter mainCharacter = (MainCharacter)character;
        EnemyCharacter enemyCharacter = (EnemyCharacter)otherCharacter;

        for (int i = 0; i < this.components.length; i++)
        {
            KillBonusGranterComponent component = this.components[i];
            component.grantKillBonuses(mainCharacter, enemyCharacter);
        }
    }
}
