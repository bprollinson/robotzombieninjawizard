package rznw.game.maincharacter.inventory;

import rznw.game.StatusEffects;
import rznw.game.maincharacter.MainCharacter;
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

        if (mainCharacter.getStatusEffects().regenerateShopEnabled())
        {
            mainCharacter.getStatusEffects().setStatusEffect(StatusEffects.EFFECT_REGENERATE_SHOP, false);

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
        Vector<InventoryItemGroup> selectedItems = new Vector<InventoryItemGroup>();

        for (int i = 0; i < RandomInventoryGenerator.NUM_ITEMS; i++)
        {
            int randomIndex = RandomNumberGenerator.randomInteger(0, possibleItems.size() - 1);
            InventoryItem item = possibleItems.get(randomIndex);
            selectedItems.add(new InventoryItemGroup(item, 1));
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
        Vector<EquipmentGroup> selectedEquipments = new Vector<EquipmentGroup>();

        for (int i = 0; i < RandomInventoryGenerator.NUM_EQUIPMENTS; i++)
        {
            int randomIndex = RandomNumberGenerator.randomInteger(0, possibleEquipments.size() - 1);
            EquipmentItem equipment = possibleEquipments.get(randomIndex);
            selectedEquipments.add(new EquipmentGroup(equipment, 1));
            possibleEquipments.remove(randomIndex);
        }

        gameWorld.getShopInventory().setRandomEquipment(selectedEquipments);
    }
}
