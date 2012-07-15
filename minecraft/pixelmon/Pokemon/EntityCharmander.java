package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityCharmander extends EntityGroundPixelmon
{
	
	public EntityCharmander(World world)
	{
		super(world);
		texture = "/pixelmon/image/charmander.png";
		init();
	}

	public void init()
	{
		name = "Charmander";
		isImmuneToFire = true;
		super.init();
		this.litUp = true;
		this.litLevel = 40;
	}
	
	public void evolve() 
	{
		BaseEntityPixelmon entity = new EntityCharmeleon(worldObj);
		helper.evolve(entity.helper);
	}
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
	    return this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.sand.blockID && this.worldObj.getBlockId(var1, var2 - 1, var3) == Block.grass.blockID;
	}

}
