package ganymedes01.ganyssurface.blocks;

import ganymedes01.ganyssurface.GanysSurface;
import ganymedes01.ganyssurface.core.proxy.ClientProxy;
import ganymedes01.ganyssurface.core.utils.Utils;
import ganymedes01.ganyssurface.lib.ModIDs;
import ganymedes01.ganyssurface.lib.RenderIDs;
import ganymedes01.ganyssurface.lib.Strings;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Gany's Surface
 * 
 * @author ganymedes01
 * 
 */

public class SlimeBlock extends Block {

	@SideOnly(Side.CLIENT)
	private Icon[] blockIcons, insideIcons;

	protected SlimeBlock() {
		super(ModIDs.SLIME_BLOCK_ID, Material.cloth);
		setHardness(2.0F);
		setTickRandomly(true);
		setCreativeTab(GanysSurface.surfaceTab);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.SLIME_BLOCK_NAME));
		setTextureName(Utils.getBlockTexture(Strings.SLIME_BLOCK_NAME, false));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			int blockID = world.getBlockId(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
			int blockMeta = world.getBlockMetadata(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);

			if (blockID == Block.waterStill.blockID && blockMeta == 0) {
				world.setBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, this.blockID);
				return;
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(Item.slimeBall, 4));
		return list;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public int getRenderType() {
		return RenderIDs.SLIME_BLOCK;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderInPass(int pass) {
		ClientProxy.renderPass = pass;
		return true;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);

		float f = 0.0625F;
		if (meta == 0)
			setBlockBounds(0F, 0F, 0F, 1.0F, 1.0F, 1.0F);
		else
			setBlockBounds(f * 2, f * 2, f * 2, f * 14, f * 14, f * 14);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return meta == 0 ? blockIcons[side] : insideIcons[side];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcons = new Icon[6];
		insideIcons = new Icon[6];
		String[] dirs = new String[] { "top", "bottom", "front", "back", "left", "right" };

		for (int i = 0; i < 6; i++) {
			blockIcons[i] = reg.registerIcon(Utils.getBlockTexture(Strings.SLIME_BLOCK_NAME, true) + dirs[i]);
			insideIcons[i] = reg.registerIcon(Utils.getBlockTexture(Strings.SLIME_BLOCK_NAME, true) + "inside_" + dirs[i]);
		}
	}
}