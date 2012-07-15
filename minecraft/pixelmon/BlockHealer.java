package pixelmon;

import java.util.Random;

import pixelmon.gui.EnumGui;
import pixelmon.gui.GuiHealer;

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
        if (!isActive)
        {
            return;
        }

        int i1 = world.getBlockMetadata(i, j, k);
        float f = (float)i + 0.5F;
        float f1 = (float)j + 0.6F + (random.nextFloat() * 6F) / 16F;
        float f2 = (float)k + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;

        if (i1 == 4)
        {
            world.spawnParticle("reddust", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("reddust", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        }
        else if (i1 == 5)
        {
            world.spawnParticle("reddust", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("reddust", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        }
        else if (i1 == 2)
        {
            world.spawnParticle("reddust", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("reddust", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
        }
        else if (i1 == 3)
        {
            world.spawnParticle("reddust", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("reddust", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        }
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
		TileEntityPokemonHealer tileentityfurnace = (TileEntityPokemonHealer) par1World
				.getBlockTileEntity(j, k, par4);

		if (tileentityfurnace != null) {
			par5EntityPlayer.openGui(mod_Pixelmon.instance, EnumGui.Healer.getIndex(), par1World, j, k, par4);
		}

		return true;
	}

	/**
	 * Update which block ID the furnace is using depending on whether or not it
	 * is burning
	 */
	public static void updateFurnaceBlockState(boolean par0, World par1World,
			int par2, int par3, int par4) {
		int i = par1World.getBlockMetadata(par2, par3, par4);
		TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
		keepFurnaceInventory = true;
		if (par0) {
			par1World.setBlockWithNotify(par2, par3, par4,
					mod_Pixelmon.healerActive.blockID);
		} else {
			par1World.setBlockWithNotify(par2, par3, par4,
					mod_Pixelmon.healerIdle.blockID);
		}

		keepFurnaceInventory = false;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, i);

		if (tileentity != null) {
			tileentity.validate();
			par1World.setBlockTileEntity(par2, par3, par4, tileentity);
		}
	}

	/**
	 * Returns the TileEntity used by this block.
	 */
	public TileEntity getBlockEntity() {
		return new TileEntityPokemonHealer();
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
		if (!keepFurnaceInventory) {
			TileEntityPokemonHealer tileentityfurnace = (TileEntityPokemonHealer) par1World
					.getBlockTileEntity(par2, par3, par4);

			if (tileentityfurnace != null) {
				label0:

				for (int i = 0; i < tileentityfurnace.getSizeInventory(); i++) {
					ItemStack itemstack = tileentityfurnace.getStackInSlot(i);

					if (itemstack == null) {
						continue;
					}

					float f = furnaceRand.nextFloat() * 0.8F + 0.1F;
					float f1 = furnaceRand.nextFloat() * 0.8F + 0.1F;
					float f2 = furnaceRand.nextFloat() * 0.8F + 0.1F;

					do {
						if (itemstack.stackSize <= 0) {
							continue label0;
						}

						int j = furnaceRand.nextInt(21) + 10;

						if (j > itemstack.stackSize) {
							j = itemstack.stackSize;
						}

						itemstack.stackSize -= j;
						EntityItem entityitem = new EntityItem(par1World,
								(float) par2 + f, (float) par3 + f1,
								(float) par4 + f2, new ItemStack(
										itemstack.itemID, j,
										itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.item.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (float) furnaceRand.nextGaussian()
								* f3;
						entityitem.motionY = (float) furnaceRand.nextGaussian()
								* f3 + 0.2F;
						entityitem.motionZ = (float) furnaceRand.nextGaussian()
								* f3;
						par1World.spawnEntityInWorld(entityitem);
					} while (true);
				}
			}
		}

		super.onBlockRemoval(par1World, par2, par3, par4);
	}
}
