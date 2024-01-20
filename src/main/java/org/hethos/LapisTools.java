package org.hethos;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class LapisTools implements ModInitializer {
	public static class registerItems {
		// Armour
		public static final ArmorMaterial LAPIS_ARMOUR_MATERIAL = new LapisArmorMaterial();
		public static final Item LAPIS_MATERIAL_HELMET = new ArmorItem(LAPIS_ARMOUR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings());
		public static final Item LAPIS_MATERIAL_CHESTPLATE = new ArmorItem(LAPIS_ARMOUR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings());
		public static final Item LAPIS_MATERIAL_LEGGINGS = new ArmorItem(LAPIS_ARMOUR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings());
		public static final Item LAPIS_MATERIAL_BOOTS = new ArmorItem(LAPIS_ARMOUR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings());

		// Tools
		public static ToolItem LAPIS_SWORD = new SwordItem(LapisToolMaterial.INSTANCE, 5, -2.8f, new FabricItemSettings());
		public static ToolItem LAPIS_PICKAXE = new PickaxeItem(LapisToolMaterial.INSTANCE, 3, -3f, new FabricItemSettings());
		public static ToolItem LAPIS_HOE = new HoeItem(LapisToolMaterial.INSTANCE, 0, -0.2f, new FabricItemSettings());
		public static ToolItem LAPIS_AXE = new AxeItem(LapisToolMaterial.INSTANCE, 7, -3.2f, new FabricItemSettings());
		public static ToolItem LAPIS_SHOVEL = new ShovelItem(LapisToolMaterial.INSTANCE, 3, -3.2f, new FabricItemSettings());

		public static void register() {
			// Register Tools
			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_sword"), LAPIS_SWORD);

			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_pickaxe"), LAPIS_PICKAXE);

			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_axe"), LAPIS_AXE);

			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_shovel"), LAPIS_SHOVEL);

			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_hoe"), LAPIS_HOE);

			// Register Armour
			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_helmet"), LAPIS_MATERIAL_HELMET);

			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_chestplate"), LAPIS_MATERIAL_CHESTPLATE);

			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_leggings"), LAPIS_MATERIAL_LEGGINGS);

			Registry.register(Registries.ITEM, new Identifier("lapis_tools", "lapis_boots"), LAPIS_MATERIAL_BOOTS);

			// Add sword and axe to create inventory combat section
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
				content.addBefore(Items.DIAMOND_SWORD, LAPIS_SWORD);
				content.addBefore(Items.DIAMOND_AXE, LAPIS_AXE);
			});

			// Add tools to create inventory tools section
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
				content.addBefore(Items.DIAMOND_SHOVEL, LAPIS_SHOVEL);
				content.addAfter(LAPIS_SHOVEL, LAPIS_PICKAXE);
				content.addAfter(LAPIS_PICKAXE, LAPIS_AXE);
				content.addAfter(LAPIS_AXE, LAPIS_HOE);
			});
		}
	}

	public static class LapisArmorMaterial implements ArmorMaterial {
		private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
		private static final int[] PROTECTION_VALUES = new int[] {2, 6, 7, 2};

		@Override
		public int getDurability(ArmorItem.Type type) {
			return BASE_DURABILITY[type.getEquipmentSlot().getEntitySlotId()] * 25;
		}

		@Override
		public int getProtection(ArmorItem.Type type) {
			return PROTECTION_VALUES[type.getEquipmentSlot().getEntitySlotId()];
		}

		@Override
		public int getEnchantability() {
			return 40;
		}

		@Override
		public SoundEvent getEquipSound() {
			return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(Items.LAPIS_LAZULI);
		}

		@Override
		public String getName() {
			return "lapis";
		}

		@Override
		public float getToughness() {
			return 2;
		}

		@Override
		public float getKnockbackResistance() {
			return 0;
		}
	}

	public static class LapisToolMaterial implements ToolMaterial {
		@Override
		public int getDurability() {
			return 400;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 7f;
		}

		@Override
		public float getAttackDamage() {
			return 0f;
		}

		@Override
		public int getMiningLevel() {
			return 3;
		}

		@Override
		public int getEnchantability() {
			return 50;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(Items.LAPIS_LAZULI);
		}

		public static final LapisToolMaterial INSTANCE = new LapisToolMaterial();
	}

	@Override
	public void onInitialize() {
		// Register Items
		registerItems.register();
	}
}