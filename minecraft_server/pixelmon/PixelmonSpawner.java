package pixelmon;

import pixelmon.entities.*;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.EnumHelper;

public class PixelmonSpawner {

	public void init() {
		PixelmonEntityList.addSpawns();

		try {
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.monster, "e", 10);
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.creature, "e", 200);
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.waterCreature, "e", 200);
		} catch (Throwable e) {
			System.err.println(e);
			try{
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.monster, "maxNumberOfCreature", 10);
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.creature, "maxNumberOfCreature", 200);
			ModLoader.setPrivateValue(EnumCreatureType.class, EnumCreatureType.waterCreature, "maxNumberOfCreature", 200);
			}catch(Throwable f){
				System.err.println(f);
			}
		}
	}
}
