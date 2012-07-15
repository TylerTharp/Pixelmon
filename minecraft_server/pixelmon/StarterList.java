package pixelmon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import pixelmon.Pokemon.EntityBulbasaur;
import pixelmon.Pokemon.EntityCharmander;
import pixelmon.Pokemon.EntityEevee;
import pixelmon.Pokemon.EntitySquirtle;
import pixelmon.attacks.Attack;
import pixelmon.database.DatabaseMoves;
import pixelmon.entities.BaseEntityPixelmon;

public class StarterList {
	private static String[] starterList = new String[]{"Bulbasaur", "Squirtle", "Charmander", "Eevee"};
	public static String[] getStarterStringList(){
		return starterList;
	}
}
