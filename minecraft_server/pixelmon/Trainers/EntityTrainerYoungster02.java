package pixelmon.Trainers;

import pixelmon.entities.EntityTrainer;
import net.minecraft.src.World;

public class EntityTrainerYoungster02 extends EntityTrainer {

	public EntityTrainerYoungster02(World par1World) {
		super(par1World);
		texture = "/pixelmon/image/youngster2.png";
		init();
	}

	public void init(){
		name = "Youngster";
		super.init();
	}
}
