package pixelmon.entities.pixelmon.helpers;

import pixelmon.entities.pixelmon.BaseEntityPixelmon;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

public class WildPokemonHelper {

	@SuppressWarnings("unchecked")
	public static IHaveHelper getCapturedPokemonEntity(String name, World world) {
		try 
		{
			Class<? extends BaseEntityPixelmon> entity = (Class<? extends BaseEntityPixelmon>) Class.forName("Pokemon.Entity" + name); 
			return  (IHaveHelper) entity.getConstructor(new Class[] { World.class })
					.newInstance(new Object[] { world });
		} catch (Exception e) 
		{
			System.err.println("Can not find class Entity" + name );
			e.printStackTrace();
		}
		return null;
	}

}
