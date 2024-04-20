package rznw.save;

import rznw.game.enemy.Assassin;
import rznw.game.enemy.BeastSummoner;
import rznw.game.enemy.Crusher;
import rznw.game.enemy.Dragon;
import rznw.game.enemy.Enchanter;
import rznw.game.enemy.EnemyCharacter;
import rznw.game.enemy.FumeBeast;
import rznw.game.enemy.GravityWizard;
import rznw.game.enemy.HealthNinja;
import rznw.game.enemy.InvisibleWizard;
import rznw.game.enemy.Javelineer;
import rznw.game.enemy.KingLizardWizard;
import rznw.game.enemy.Leech;
import rznw.game.enemy.Mummy;
import rznw.game.enemy.Nosferatu;
import rznw.game.enemy.Oni;
import rznw.game.enemy.Phantasm;
import rznw.game.enemy.QuicksandDweller;
import rznw.game.enemy.RockMan;
import rznw.game.enemy.Sphinx;
import rznw.game.enemy.Thief;
import rznw.game.enemy.Undertaker;
import rznw.game.enemy.Viper;
import rznw.game.enemy.Werewolf;
import rznw.game.enemy.XRayCat;
import rznw.game.enemy.Yeti;
import rznw.game.enemy.Zenith;

public class EnemyCharacterFactory
{
    public static EnemyCharacter factory(int enemyIndex, int level)
    {
        switch (enemyIndex)
        {
            case Assassin.ENEMY_NUMBER:
                return new Assassin(level);
            case BeastSummoner.ENEMY_NUMBER:
                return new BeastSummoner(level);
            case Crusher.ENEMY_NUMBER:
                return new Crusher(level);
            case Dragon.ENEMY_NUMBER:
                return new Dragon(level);
            case Enchanter.ENEMY_NUMBER:
                return new Enchanter(level);
            case FumeBeast.ENEMY_NUMBER:
                return new FumeBeast(level);
            case GravityWizard.ENEMY_NUMBER:
                return new GravityWizard(level);
            case HealthNinja.ENEMY_NUMBER:
                return new HealthNinja(level);
            case InvisibleWizard.ENEMY_NUMBER:
                return new InvisibleWizard(level);
            case Javelineer.ENEMY_NUMBER:
                return new Javelineer(level);
            case KingLizardWizard.ENEMY_NUMBER:
                return new KingLizardWizard(level);
            case Leech.ENEMY_NUMBER:
                return new Leech(level);
            case Mummy.ENEMY_NUMBER:
                return new Mummy(level);
            case Nosferatu.ENEMY_NUMBER:
                return new Nosferatu(level);
            case Oni.ENEMY_NUMBER:
                return new Oni(level);
            case Phantasm.ENEMY_NUMBER:
                return new Phantasm(level);
            case QuicksandDweller.ENEMY_NUMBER:
                return new QuicksandDweller(level);
            case RockMan.ENEMY_NUMBER:
                return new RockMan(level);
            case Sphinx.ENEMY_NUMBER:
                return new Sphinx(level);
            case Thief.ENEMY_NUMBER:
                return new Thief(level);
            case Undertaker.ENEMY_NUMBER:
                return new Undertaker(level);
            case Viper.ENEMY_NUMBER:
                return new Viper(level);
            case Werewolf.ENEMY_NUMBER:
                return new Werewolf(level);
            case XRayCat.ENEMY_NUMBER:
                return new XRayCat(level);
            case Yeti.ENEMY_NUMBER:
                return new Yeti(level);
            case Zenith.ENEMY_NUMBER:
                return new Zenith(level);
        }

        return null;
    }
}
