package pixelmon.entities;

import net.minecraft.src.*;

public class EntityQuestionMarks extends EntityLiving
{
	
	public EntityQuestionMarks(World world)
	{
		super(world);
		texture = "/pixelmon/image/questionmarks.png";
		height = 0.2F;
	}

	public int getMaxHealth() 
	{
		return 0;
	}
	
}