package pixelmon.blocks;

import java.util.Random;

import pixelmon.enums.EnumGui;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;

public class BlockHealer extends BlockContainer {
	/**
	 * Is the random generator used by furnace to drop the inventory contents in
	 * random directions.
	 */
	private Random furnaceRand;
	private static final int hTop = ModLoader.addOverride("/terrain.png", "/pixelmon/block/healertop.png");
	private static final int hBottom = ModLoader.addOverride("/terrain.png", "/pixelmon/block/healerbottom.png");
	private static final int hSide = ModLoader.addOverride("/terrain.png", "/pixelmon/block/healerside.png");
	private static final int hFrontActive = ModLoader.addOverride("/terrain.png", "/pixelmon/block/healerfrontactive.png");
	private static final int hFrontInactive = ModLoader.addOverride("/terrain.png", "/pixelmon/block/healerfrontinactive.png");

	/** True if this is an active furnace, false if idle */
	private final boolean isActive;

	/**
	 * This flag is used to prevent the furnace inventory to be dropped upon
	 * block removal, is used internally when the furnace block changes from
	 * idle to active and vice-versa.
	 */
	private static boolean keepFurnaceInventory = false;

	public BlockHealer(int par1, boolean par2) {
		super(par1, Material.rock);
		furnaceRand = new Random();
		isActive = par2;
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int par1, Random par2Random, int par3) {
		return -1;//mod_Pixelmon.healerIdle.blockID;
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		setDefaultDirection(par1World, par2, par3, par4);
	}

	/**
	 * set a blocks direction
	 */
	private void setDefaultDirection(World par1World, int par2, int par3,
			int par4) {
		if (par1World.isRemote) {
			return;
		}

		int i = par1World.getBlockId(par2, par3, par4 - 1);
		int j = par1World.getBlockId(par2, par3, par4 + 1);
		int k = par1World.getBlockId(par2 - 1, par3, par4);
		int l = par1World.getBlockId(par2 + 1, par3, par4);
		byte byte0 = 3;

		if (Block.opaqueCubeLookup[i] && !Block.opaqueCubeLookup[j]) {
			byte0 = 3;
		}

		if (Block.opaqueCubeLookup[j] && !Block.opaqueCubeLookup[i]) {
			byte0 = 2;
		}

		if (Block.opaqueCubeLookup[k] && !Block.opaqueCubeLookup[l]) {
			byte0 = 5;
		}

		if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[k]) {
			byte0 = 4;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, byte0);
	}

	/**
	 * Retrieves the block texture to use based on the display side. Args:
	 * iBlockAccess, x, y, z, side
	 */
	public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {
		if (par5 == 1) {
			return hTop;//top
		}

		if (par5 == 0) {
			return hBottom;//bottom
		}

		int i = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

		if (par5 != i) {
			return hSide;//side
		}

		if (isActive) {
			return hFrontActive;//front active
		} else {
			return hFrontInactive;//front inactive
		}
	}

	/**
	 * Returns the block texture based on the side being looked at. Args: side
	 */
	public int getBlockTextureFromSide(int par1) {
		if (par1 == 1) {
			return hTop;//top
		}

		if (par1 == 0) {
			return hBottom;//bottom
		}

		if (par1 == 3) {
			return hFrontInactive;//front inactive
		} else {
			return hSide;//side
		}
	}

	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
       
	}
	
	/**
	 * Called upon block activation (left or right click on the block.). The
	 * three integers represent x,y,z of the block.
	 */
	public boolean blockActivated(World par1World, int j, int k,
			int par4, EntityPlayer par5EntityPlayer) {
		if (par1World.isRemote) {
			return true;
		}
		par5EntityPlayer.openGui(mod_Pixelmon.instance, EnumGui.Healer.getIndex(), par1World, 0,0,0);
		
		return true;
	}

	/**
	 * Update which block ID the furnace is using depending on whether or not it
	 * is burning
	 */
	public static void updateFurnaceBlockState(boolean par0, World par1World,
			int par2, int par3, int par4) {
		
	}

	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving) {
		int i = MathHelper
				.floor_double((double) ((par5EntityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		if (i == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
		}

		if (i == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
		}

		if (i == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
		}

		if (i == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
		}
	}

	/**
	 * Called whenever the block is removed.
	 */
	public void onBlockRemoval(World par1World, int par2, int par3, int par4) {
		super.onBlockRemoval(par1World, par2, par3, par4);
	}

	@Override
	public TileEntity getBlockEntity() {
		return null;
	}
}
