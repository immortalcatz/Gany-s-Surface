package ganymedes01.ganyssurface.blocks;

import ganymedes01.ganyssurface.GanysSurface;
import ganymedes01.ganyssurface.core.utils.Utils;
import ganymedes01.ganyssurface.lib.Strings;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FertilizedSoil extends Block {

	public FertilizedSoil(int id) {
		super(id, Material.ground);
		setHardness(0.6F);
		setLightOpacity(255);
		setTickRandomly(true);
		setStepSound(soundGravelFootstep);
		setCreativeTab(GanysSurface.surfaceTab);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
		setUnlocalizedName(Utils.getUnlocalizedName(Strings.FERTILIZED_SOIL_NAME));
	}

	@Override
	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
		return Block.tilledField.canSustainPlant(world, x, y, z, direction, plant);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		updateTick(world, x, y, z, new Random());
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (isWaterNearby(world, x, y, z))
			if (Block.blocksList[world.getBlockId(x, y + 1, z)] instanceof IPlantable)
				if (world.getBlockMetadata(x, y + 1, z) < 7)
					world.setBlockMetadataWithNotify(x, y + 1, z, world.getBlockMetadata(x, y + 1, z) + 1, 2);
				else if (rand.nextInt(50) == 25)
					world.setBlock(x, y, z, Block.tilledField.blockID);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	private boolean isWaterNearby(World world, int x, int y, int z) {
		for (int i = x - 4; i <= x + 4; i++)
			for (int j = y; j <= y + 1; j++)
				for (int k = z - 4; k <= z + 4; k++)
					if (world.getBlockMaterial(i, j, k) == Material.water)
						return true;
		return false;
	}

	@Override
	public int idDropped(int id, Random rand, int par3) {
		return Block.dirt.blockID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return side == 1 ? blockIcon : Block.dirt.getBlockTextureFromSide(side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		blockIcon = reg.registerIcon(Utils.getBlockTexture(Strings.FERTILIZED_SOIL_NAME, false));
	}
}