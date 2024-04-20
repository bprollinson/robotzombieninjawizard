package rznw.game.maincharacter.inventory;

import rznw.game.maincharacter.MainCharacter;
import rznw.game.statuseffects.SimpleStatusEffects;
import rznw.map.GameWorld;
import rznw.utility.RandomNumberGenerator;

import java.util.Arrays;
import java.util.Vector;

public class RandomInventoryGenerator
{
    private static final int NUM_ITEMS = 2;
    private static final int NUM_EQUIPMENTS = 2;

    public static void handleRegeneration(GameWorld gameWorld)
    {
        MainCharacter mainCharacter = gameWorld.getMainCharacter();

        if (mainCharacter.getStatusEffects().getStatusEffect(SimpleStatusEffects.EFFECT_REGENERATE_SHOP))
        {
            mainCharacter.getStatusEffects().setStatusEffect(SimpleStatusEffects.EFFECT_REGENERATE_SHOP, false);

            RandomInventoryGenerator.regenerateSelectedItems(gameWorld);
            RandomInventoryGenerator.regenerateSelectedEquipments(gameWorld);
        }
    }

    private static void regenerateSelectedItems(GameWorld gameWorld)
    {
        InventoryItem[] possibleItemsArray = new InventoryItem[] {
            new Bomb(),
            new FullManaPotion(),
            new FullPotion(),
            new Herb(),
            new ManaPotion(),
            new Potion(),
            new SanityDrop(),
            new XRayDrop()
        };
        Vector<InventoryItem> possibleItems = new Vector<InventoryItem>(Arrays.asList(possibleItemsArray));
        Inventory selectedItems = new Inventory(null);

        for (int i = 0; i < RandomInventoryGenerator.NUM_ITEMS; i++)
        {
            int randomIndex = RandomNumberGenerator.randomInteger(0, possibleItems.size() - 1);
            InventoryItem item = possibleItems.get(randomIndex);
            try
            {
                selectedItems.addItems(new InventoryItemGroup(item, 1));
            }
            catch (InventoryFullException ife)
            {
            }
            possibleItems.remove(randomIndex);
        }

        gameWorld.getShopInventory().setRandomItems(selectedItems);
    }

    private static void regenerateSelectedEquipments(GameWorld gameWorld)
    {
        EquipmentItem[] possibleEquipmentsArray = new EquipmentItem[] {
            new DeathScythe(),
            new RiddleWand(),
            new GravityBlade(),
            new QuicksandHammer(),
            new IceRod(),
            new ThiefGlove(),
            new WandOfSummoning(),
            new InvisibilityWand(),
            new ViperDagger(),
            new GauntletOfDarkness(),
            new MagicJavelin(),
            new BloodSword(),
            new CrusherHammer(),
            new WoodenSword(),
            new LeechMail(),
            new PoisonCloth(),
            new AssassinsCloak(),
            new DragonPlate(),
            new ProtectivePlate(),
            new RockMail(),
            new ShieldOfSight(),
            new HealShield(),
            new MagicShield(),
            new WoodenShield(),
            new EtherealShield()
        };
        Vector<EquipmentItem> possibleEquipments = new Vector<EquipmentItem>(Arrays.asList(possibleEquipmentsArray));
        Equipment selectedEquipments = new Equipment(null);

        for (int i = 0; i < RandomInventoryGenerator.NUM_EQUIPMENTS; i++)
        {
            int randomIndex = RandomNumberGenerator.randomInteger(0, possibleEquipments.size() - 1);
            EquipmentItem equipment = possibleEquipments.get(randomIndex);
            try
            {
                selectedEquipments.addEquipment(new EquipmentGroup(equipment, 1));
            }
            catch (EquipmentFullException ife)
            {
            }
            possibleEquipments.remove(randomIndex);
        }

        gameWorld.getShopInventory().setRandomEquipment(selectedEquipments);
    }
}
