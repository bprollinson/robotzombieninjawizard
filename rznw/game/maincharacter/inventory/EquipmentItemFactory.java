package rznw.game.maincharacter.inventory;

public class EquipmentItemFactory
{
    public static EquipmentItem factory(int equipmentIndex)
    {
        switch (equipmentIndex)
        {
            case BloodSword.EQUIPMENT_NUMBER:
                return new BloodSword();
            case CrusherHammer.EQUIPMENT_NUMBER:
                return new CrusherHammer();
            case DeathScythe.EQUIPMENT_NUMBER:
                return new DeathScythe();
            case GauntletOfDarkness.EQUIPMENT_NUMBER:
                return new GauntletOfDarkness();
            case GravityBlade.EQUIPMENT_NUMBER:
                return new GravityBlade();
            case IceRod.EQUIPMENT_NUMBER:
                return new IceRod();
            case InvisibilityWand.EQUIPMENT_NUMBER:
                return new InvisibilityWand();
            case MagicJavelin.EQUIPMENT_NUMBER:
                return new MagicJavelin();
            case QuicksandHammer.EQUIPMENT_NUMBER:
                return new QuicksandHammer();
            case RiddleWand.EQUIPMENT_NUMBER:
                return new RiddleWand();
            case ThiefGlove.EQUIPMENT_NUMBER:
                return new ThiefGlove();
            case ViperDagger.EQUIPMENT_NUMBER:
                return new ViperDagger();
            case WandOfSummoning.EQUIPMENT_NUMBER:
                return new WandOfSummoning();
            case WoodenSword.EQUIPMENT_NUMBER:
                return new WoodenSword();
            case ZenithWand.EQUIPMENT_NUMBER:
                return new ZenithWand();
            case EtherealShield.EQUIPMENT_NUMBER:
                return new EtherealShield();
            case HealShield.EQUIPMENT_NUMBER:
                return new HealShield();
            case MagicShield.EQUIPMENT_NUMBER:
                return new MagicShield();
            case ShieldOfSight.EQUIPMENT_NUMBER:
                return new ShieldOfSight();
            case WoodenShield.EQUIPMENT_NUMBER:
                return new WoodenShield();
            case AssassinsCloak.EQUIPMENT_NUMBER:
                return new AssassinsCloak();
            case DragonPlate.EQUIPMENT_NUMBER:
                return new DragonPlate();
            case LeechMail.EQUIPMENT_NUMBER:
                return new LeechMail();
            case PoisonCloth.EQUIPMENT_NUMBER:
                return new PoisonCloth();
            case ProtectivePlate.EQUIPMENT_NUMBER:
                return new ProtectivePlate();
            case RockMail.EQUIPMENT_NUMBER:
                return new RockMail();
        }

        return null;
    }
}
