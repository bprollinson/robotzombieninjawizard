package rznw.game.maincharacter.inventory;

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
            mainCharacter.getStatusEffects().disableRegenerateShop();

            RandomInventoryGenerator.regenerateSelectedItems(gameWorld);
            RandomInventoryGenerator.regenerateSelectedEquipments(gameWorld);
        }
    }

    private static void regenerateSelectedItems(GameWorld gameWorld)
    {
        InventoryItemGroup[] possibleItemsArray = new InventoryItemGroup[] {
            new InventoryItemGroup(new Bomb(), 1),
            new InventoryItemGroup(new FullManaPotion(), 1),
            new InventoryItemGroup(new FullPotion(), 1),
            new InventoryItemGroup(new Herb(), 1),
            new InventoryItemGroup(new ManaPotion(), 1),
            new InventoryItemGroup(new Potion(), 1),
            new InventoryItemGroup(new SanityDrop(), 1),
            new InventoryItemGroup(new XRayDrop(), 1)
        };
        Vector<InventoryItemGroup> possibleItems = new Vector<InventoryItemGroup>(Arrays.asList(possibleItemsArray));
        Vector<InventoryItemGroup> selectedItems = new Vector<InventoryItemGroup>();

        for (int i = 0; i < RandomInventoryGenerator.NUM_ITEMS; i++)
        {
            int randomIndex = RandomNumberGenerator.randomInteger(0, possibleItems.size() - 1);
            selectedItems.add(possibleItems.get(randomIndex));
            possibleItems.remove(randomIndex);
        }

        gameWorld.getShopInventory().setRandomItems(selectedItems);
    }

    private static void regenerateSelectedEquipments(GameWorld gameWorld)
    {
        EquipmentGroup[] possibleEquipmentsArray = new EquipmentGroup[] {
            new EquipmentGroup(new DeathScythe(), 1),
            new EquipmentGroup(new RiddleWand(), 1),
            new EquipmentGroup(new GravityBlade(), 1),
            new EquipmentGroup(new QuicksandHammer(), 1),
            new EquipmentGroup(new IceRod(), 1),
            new EquipmentGroup(new ThiefGlove(), 1),
            new EquipmentGroup(new WandOfSummoning(), 1),
            new EquipmentGroup(new InvisibilityWand(), 1),
            new EquipmentGroup(new ViperDagger(), 1),
            new EquipmentGroup(new GauntletOfDarkness(), 1),
            new EquipmentGroup(new MagicJavelin(), 1),
            new EquipmentGroup(new BloodSword(), 1),
            new EquipmentGroup(new CrusherHammer(), 1),
            new EquipmentGroup(new WoodenSword(), 1),
            new EquipmentGroup(new LeechMail(), 1),
            new EquipmentGroup(new PoisonCloth(), 1),
            new EquipmentGroup(new AssassinsCloak(), 1),
            new EquipmentGroup(new DragonPlate(), 1),
            new EquipmentGroup(new ProtectivePlate(), 1),
            new EquipmentGroup(new RockMail(), 1),
            new EquipmentGroup(new ShieldOfSight(), 1),
            new EquipmentGroup(new HealShield(), 1),
            new EquipmentGroup(new MagicShield(), 1),
            new EquipmentGroup(new WoodenShield(), 1),
            new EquipmentGroup(new EtherealShield(), 1)
        };
        Vector<EquipmentGroup> possibleEquipments = new Vector<EquipmentGroup>(Arrays.asList(possibleEquipmentsArray));
        Vector<EquipmentGroup> selectedEquipments = new Vector<EquipmentGroup>();

        for (int i = 0; i < RandomInventoryGenerator.NUM_EQUIPMENTS; i++)
        {
            int randomIndex = RandomNumberGenerator.randomInteger(0, possibleEquipments.size() - 1);
            selectedEquipments.add(possibleEquipments.get(randomIndex));
            possibleEquipments.remove(randomIndex);
        }

        gameWorld.getShopInventory().setRandomEquipment(selectedEquipments);
    }
}
