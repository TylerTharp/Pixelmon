package pixelmon.Pokemon;

import pixelmon.entities.pixelmon.EntityGroundPixelmon;
import net.minecraft.src.Block;
import net.minecraft.src.EntityAILookIdle;
import net.minecraft.src.EntityAISwimming;
import net.minecraft.src.EntityAITasks;
import net.minecraft.src.EntityAITempt;
import net.minecraft.src.EntityAIWander;
import net.minecraft.src.EntityAIWatchClosest;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.World;
import net.minecraft.src.mod_Pixelmon;


public class EntityAbra extends EntityGroundPixelmon
{
	
	public EntityAbra(World world)
	{
		super(world);
		init();
	}

	public void init() 
	{
		name = "Abra";
		isImmuneToFire = false;
		super.init();
		tasks = new EntityAITasks();
		tasks.addTask(1, new EntityAISwimming(this));
	}

	public void onUpdate()
	{
		super.onUpdate();
		EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, 7D);
		if(entityplayer != null)
		{
			if(getOwner() == null)
			{
				teleportRandomly();
			}
		}
	}
	
    protected boolean teleportRandomly()
    {
        double d = posX + (rand.nextDouble() - 0.5D) * 64D;
        double d1 = posY + (double)(rand.nextInt(64) - 32);
        double d2 = posZ + (rand.nextDouble() - 0.5D) * 64D;
        return teleportTo(d, d1, d2);
    }
    
    protected boolean teleportTo(double par1, double par3, double par5)
    {
        double d = posX;
        double d1 = posY;
        double d2 = posZ;
        posX = par1;
        posY = par3;
        posZ = par5;
        boolean flag = false;
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(posY);
        int k = MathHelper.floor_double(posZ);

        if (worldObj.blockExists(i, j, k))
        {
            boolean flag1;

            for (flag1 = false; !flag1 && j > 0;)
            {
                int i1 = worldObj.getBlockId(i, j - 1, k);

                if (i1 == 0 || !Block.blocksList[i1].blockMaterial.blocksMovement())
                {
                    posY--;
                    j--;
                }
                else
                {
                    flag1 = true;
                }
            }

            if (flag1)
            {
                setPosition(posX, posY, posZ);

                if (worldObj.getCollidingBoundingBoxes(this, boundingBox).size() == 0 && !worldObj.isAnyLiquid(boundingBox))
                {
                    flag = true;
                }
            }
        }

        if (!flag)
        {
            setPosition(d, d1, d2);
            return false;
        }

        int l = 128;

        for (int j1 = 0; j1 < l; j1++)
        {
            double d3 = (double)j1 / ((double)l - 1.0D);
            float f = (rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (rand.nextFloat() - 0.5F) * 0.2F;
            double d4 = d + (posX - d) * d3 + (rand.nextDouble() - 0.5D) * (double)width * 2D;
            double d5 = d1 + (posY - d1) * d3 + rand.nextDouble() * (double)height;
            double d6 = d2 + (posZ - d2) * d3 + (rand.nextDouble() - 0.5D) * (double)width * 2D;
            worldObj.spawnParticle("portal", d4, d5, d6, f, f1, f2);
        }

        worldObj.playSoundEffect(d, d1, d2, "mob.endermen.portal", 1.0F, 1.0F);
        worldObj.playSoundAtEntity(this, "mob.endermen.portal", 1.0F, 1.0F);
        return true;
    }

	public void evolve() 
	{
	}
	
}