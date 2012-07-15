package pixelmon.Pokemon;

import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.EntityGroundPixelmon;
import net.minecraft.src.*;

public class EntityCharmeleon extends EntityGroundPixelmon
{
	
	public EntityCharmeleon(World world)
	{
		super(world);
		texture = "/pixelmon/image/charmeleon.png";
		init();
	}

	public void init()
	{
		name = "Charmeleon";
		isImmuneToFire = true;
		super.init();
		this.litUp = true;
		this.litLevel = 45;
	}
	
	public void evolve() 
	{
		BaseEntityPixelmon entity = new EntityCharizard(worldObj);
		helper.evolve(entity.helper);
	}


}
