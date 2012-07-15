package net.minecraft.src;

public abstract class BlockContainer extends Block
{
    protected BlockContainer(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.isBlockContainer = true;
    }

    protected BlockContainer(int par1, int par2, Material par3Material)
    {
        super(par1, par2, par3Material);
        this.isBlockContainer = true;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        par1World.setBlockTileEntity(par2, par3, par4, this.getBlockEntity(par1World.getBlockMetadata(par2, par3, par4)));
    }

    /**
     * Called whenever the block is removed.
     */
    public void onBlockRemoval(World par1World, int par2, int par3, int par4)
    {
        super.onBlockRemoval(par1World, par2, par3, par4);
        par1World.removeBlockTileEntity(par2, par3, par4);
    }

    /**
     * Returns the TileEntity used by this block.
     */
    public abstract TileEntity getBlockEntity();

    /**
     * Called when the block receives a client event - see World.sendClientEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, event number, parameter
     */
    public void receiveClientEvent(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.receiveClientEvent(par1World, par2, par3, par4, par5, par6);
        TileEntity var7 = par1World.getBlockTileEntity(par2, par3, par4);

        if (var7 != null)
        {
            var7.receiveClientEvent(par5, par6);
        }
    }
    
    /**
     * Metatdata sensitive version of getBlockEntity
     * @param metadata The Metatdata for the current block
     * @return A instance of the TileEntity class associated with this block
     */
    public TileEntity getBlockEntity(int metadata)
    {
    	return getBlockEntity();
    }
}
