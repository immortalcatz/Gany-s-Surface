package ganymedes01.ganyssurface.items.block;

import ganymedes01.ganyssurface.core.utils.Utils;
import ganymedes01.ganyssurface.lib.Strings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Gany's Surface
 *
 * @author ganymedes01
 *
 */

public class ItemBasalt extends ItemBlock {

	public ItemBasalt(Block block) {
		super(block);
		setHasSubtypes(true);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.BASALT + "_item_"));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + stack.getItemDamage();
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
}