package ganymedes01.ganyssurface.recipes;

import ganymedes01.ganyssurface.GanysSurface;
import ganymedes01.ganyssurface.blocks.ModBlocks;
import ganymedes01.ganyssurface.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Gany's Surface
 * 
 * @author ganymedes01
 * 
 */

public class ModRecipes {

	private static String[] dyes = new String[] { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };

	public static void init() {
		registerOreDictionary();

		registerBlockRecipes();
		registerItemRecipes();
		registerArmourRecipes();
	}

	private static void registerOreDictionary() {
		if (OreDictionary.getOres("egg").isEmpty())
			OreDictionary.registerOre("egg", new ItemStack(Items.egg));
		OreDictionary.registerOre("mobEgg", ModItems.chargedCreeperSpawner);
		OreDictionary.registerOre("slimeball", Items.slime_ball);
		OreDictionary.registerOre("dustRedstone", new ItemStack(ModItems.colouredRedstone, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("mobEgg", new ItemStack(ModItems.horseSpawner, 1, OreDictionary.WILDCARD_VALUE));
	}

	private static void registerArmourRecipes() {
		if (GanysSurface.enableWoodenArmour) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.woodenHelmet), "xxx", "x x", 'x', "logWood"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.woodenChestplate), "x x", "xxx", "xxx", 'x', "logWood"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.woodenLeggings), "xxx", "x x", "x x", 'x', "logWood"));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.woodenBoots), "x x", "x x", 'x', "logWood"));
		}
		GameRegistry.addRecipe(new RecipeArmourDyes());
	}

	private static void registerItemRecipes() {
		GameRegistry.addRecipe(new ItemStack(ModItems.rot, 4), "xxx", "xyx", "xxx", 'x', Items.rotten_flesh, 'y', Blocks.dirt);
		GameRegistry.addRecipe(new ItemStack(ModItems.teaBag), " y ", "yxy", " y ", 'x', ModItems.teaLeaves, 'y', Items.string);
		GameRegistry.addRecipe(new ItemStack(ModItems.emptyMug, 5), "x x", "xxx", 'x', Items.clay_ball);
		GameRegistry.addRecipe(new ItemStack(ModItems.cupOfTea), " x ", "yaz", " b ", 'x', Items.milk_bucket, 'y', Items.potionitem, 'z', Items.sugar, 'a', ModItems.teaBag, 'b', ModItems.emptyMug);
		GameRegistry.addRecipe(new ItemStack(ModItems.mankyCupOfTea), " y ", "xaz", " b ", 'x', Items.milk_bucket, 'y', Items.potionitem, 'z', Items.sugar, 'a', ModItems.teaBag, 'b', ModItems.emptyMug);
		GameRegistry.addRecipe(new ItemStack(ModItems.rot, 8, 1), "xxx", "xyx", "xxx", 'x', new ItemStack(ModItems.poop, 1, 0), 'y', Blocks.dirt);
		GameRegistry.addRecipe(new ItemStack(ModItems.rot, 16, 1), "xxx", "xyx", "xxx", 'x', new ItemStack(ModItems.poop, 1, 1), 'y', Blocks.dirt);
		for (ItemStack egg : OreDictionary.getOres("egg"))
			GameRegistry.addSmelting(egg, new ItemStack(ModItems.cookedEgg), 0.5F);

		GameRegistry.addRecipe(new ItemStack(ModItems.obsidianHead), " x ", "xyx", 'x', Blocks.obsidian, 'y', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(ModItems.woodenWrench), "x x", " x ", " x ", 'x', Blocks.planks);
		GameRegistry.addRecipe(new ItemStack(ModItems.batNet), "xyx", " x ", " x ", 'x', Items.stick, 'y', Items.string);
		GameRegistry.addRecipe(new ItemStack(ModItems.batStew), "xyz", " w ", 'x', Blocks.brown_mushroom, 'z', Blocks.red_mushroom, 'y', ModItems.pocketCritter, 'w', Items.bowl);
		if (GanysSurface.activateChocolate)
			GameRegistry.addRecipe(new ItemStack(ModItems.chocolateBar, 4), "xxx", "xyx", "xxx", 'x', new ItemStack(Items.dye, 1, 3), 'y', Items.milk_bucket);
		GameRegistry.addRecipe(new ItemStack(ModItems.horsalyser), "xyx", "xzx", "xwx", 'x', Items.leather, 'y', Items.flint, 'z', Blocks.glass_pane, 'w', Items.redstone);

		for (int i = 0; i < dyes.length; i++)
			for (ItemStack dye : OreDictionary.getOres(dyes[i]))
				if (i != 1) { // Skip Red
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.colouredRedstone, 8, i), "xxx", "xyx", "xxx", 'x', "dustRedstone", 'y', dye));
					GameRegistry.addShapelessRecipe(new ItemStack(ModItems.colouredRedstone, 9, i), new ItemStack(ModBlocks.colouredRedstoneBlock, 1, i));
				} else
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.redstone, 8), "xxx", "xyx", "xxx", 'x', "dustRedstone", 'y', dye));
		GameRegistry.addRecipe(new ItemStack(ModItems.villageFinder), "xxx", "xyx", "xxx", 'x', Items.leather, 'y', Items.ender_pearl);
		GameRegistry.addRecipe(new ItemStack(ModItems.portalDualWorkTable), "y ", " x", 'x', ModItems.batNet, 'y', ModBlocks.dualWorkTable);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.icyPickaxe), "xxx", " y ", " y ", 'x', Blocks.ice, 'y', "stickWood"));
		GameRegistry.addSmelting(new ItemStack(ModItems.pocketCritter, 1, 1), new ItemStack(ModItems.roastedSquid), 0.5F);
		GameRegistry.addRecipe(new StorageCaseRecipe());

		// Vanilla
		GameRegistry.addRecipe(new ItemStack(Items.clay_ball, 8), "xxx", "yzy", "xxx", 'x', Blocks.gravel, 'y', Blocks.dirt, 'z', Items.water_bucket);
		GameRegistry.addRecipe(new ItemStack(Items.name_tag), " y ", "x  ", 'x', Items.paper, 'y', Items.string);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.slime_ball, 8), ModBlocks.slimeBlock);
	}

	private static void registerBlockRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.dislocator), "zxz", "y y", "zyz", 'x', ModItems.obsidianHead, 'y', "plankWood", 'z', Items.iron_ingot));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.sensoringDislocator), "wxw", "zyz", "wzw", 'x', ModBlocks.dislocator, 'y', ModBlocks.blockDetector, 'z', new ItemStack(Blocks.stone_slab, 1, 0), 'w', Items.gold_ingot);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.cubicSensoringDislocator), "xxy", "xwx", "yxx", 'x', ModBlocks.sensoringDislocator, 'y', Blocks.glass, 'w', Items.diamond);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.rainDetector), "xyx", "yyy", "xyx", 'x', Items.emerald, 'y', "slabWood"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockDetector), "xyx", "yzy", "xyx", 'x', Items.redstone, 'y', new ItemStack(Blocks.stone_slab, 1, 0), 'z', Items.comparator);

		for (int i = 0; i < ModBlocks.disguisedTrapDoor.length; i++)
			GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.disguisedTrapDoor[i]), new ItemStack(Blocks.planks, 1, i), Blocks.trapdoor);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.workTable), Blocks.crafting_table, Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.organicMatterCompressor), "zzz", "zxz", "zyz", 'x', Items.cauldron, 'y', Items.emerald, 'z', Blocks.obsidian);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.cushion), "zxz", "xyx", "zxz", 'x', Blocks.wool, 'y', dyes[5], 'z', Items.gold_nugget));
		if (GanysSurface.activateChocolate)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.chocolateCake), "xxx", "yzy", "www", 'x', Items.milk_bucket, 'y', ModItems.chocolateBar, 'z', "egg", 'w', Items.wheat));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.chestPropellant), "ywy", "xzx", "xyx", 'x', Items.iron_ingot, 'y', Items.gold_nugget, 'z', new ItemStack(Blocks.sandstone, 1, 2), 'w', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.fertilizedSoil), "yyy", "xzx", "yyy", 'x', new ItemStack(ModItems.poop, 1, 1), 'y', new ItemStack(ModItems.rot, 1, 1), 'z', Blocks.dirt);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.planter), "aza", "ywy", " x ", 'x', Blocks.hopper, 'y', new ItemStack(Blocks.wool, 1, 13), 'z', Blocks.dispenser, 'w', ModBlocks.blockDetector, 'a', Blocks.stone);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.lantern), Blocks.glass, Blocks.torch);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.inkHarvester), "xyx", "xzx", "xwx", 'x', new ItemStack(ModItems.pocketCritter, 1, 1), 'y', Items.redstone, 'z', Blocks.iron_block, 'w', Items.golden_sword);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.slimeBlock), "xxx", "xyx", "xxx", 'x', "slimeball", 'y', Items.water_bucket));
		for (int i = 0; i < dyes.length; i++) {
			if (i != 1)
				GameRegistry.addRecipe(new ItemStack(ModBlocks.colouredRedstoneBlock, 1, i), "xxx", "xxx", "xxx", 'x', new ItemStack(ModItems.colouredRedstone, 1, i));
			GameRegistry.addRecipe(new ItemStack(ModBlocks.itemDisplay, 1, i), "xxx", "x x", "xyx", 'x', Blocks.glass_pane, 'y', new ItemStack(Blocks.carpet, 0, i));
		}

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.dualWorkTable), "yyy", "x x", "yyy", 'x', ModBlocks.workTable, 'y', "plankWood"));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.market), "xzx", "xyx", "xzx", 'x', Items.iron_ingot, 'y', new ItemStack(Items.dye), 'z', Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.farmManager), "xyx", "xzx", "xyx", 'x', ModBlocks.planter, 'y', Blocks.chest, 'z', Items.gold_ingot);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.encasingBench), "xyx", "yzy", "xyx", 'x', Items.gold_ingot, 'y', Blocks.piston, 'z', Blocks.chest);
		GameRegistry.addRecipe(new ItemStack(ModBlocks.autoEncaser), "xyx", "yzy", "xyx", 'x', Items.diamond, 'y', Blocks.piston, 'z', ModBlocks.encasingBench);

		// Vanilla
		GameRegistry.addRecipe(new ItemStack(Blocks.web), "x x", " y ", "x x", 'y', Items.slime_ball, 'x', Items.string);
	}
}