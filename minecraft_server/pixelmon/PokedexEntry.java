package pixelmon;

import java.math.BigDecimal;

import pixelmon.entities.EntityQuestionMarks;

import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

public class PokedexEntry
{
	
	public int pokedexNumber;
	public int region;
	public int spriteIndex;
	public String name;
	public String description;
	public float height;
	public float weight;
	private EntityLiving pixelmon;
	private Class<? extends EntityLiving> entity;
	
	@SuppressWarnings("unchecked")
	public PokedexEntry(int i, String s, String s1)
	{
		pokedexNumber = i;
		name = s;
		description = s1;
		try 
		{
			entity = (Class<? extends EntityLiving>) Class.forName("Pokemon.Entity" + name);
		} catch (ClassNotFoundException e) 
		{
			name = "???";
			description = "";
			entity = EntityQuestionMarks.class;
			setHeightAndWeight(12, 0);

		}
	}
	
	public EntityLiving getEntity(boolean flag, World world)
	{
		if(pixelmon != null) return pixelmon;
		try {
			pixelmon = (EntityLiving) entity.getConstructor(new Class[] { World.class })
					.newInstance(new Object[] { flag?world:null });
			return pixelmon;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PokedexEntry setHeightAndWeight(float d, float d1)
	{
		height = d;
		weight = d1;
		return this;
	}
	
	public String getDisplayNumber()
	{
		String s = String.valueOf(pokedexNumber);
		int i = s.length();
		StringBuilder sb = new StringBuilder();
		int i1 = 3 - i;
		for(int i2 = 0; i2 < i1; i2++)
			sb.append("0");
		sb.append(s);
		return sb.toString();
	}
	
	public String getDisplayHeightFeet()
	{
		if(height == 0.0 || name == "???")
			return "??? ft";
		float d = height * 3.2808399F;
		String s = String.valueOf(d);
		s = s.replace('.', '%');
		String[] sa = s.split("%");
		String s1 = sa[0];
		String s2 = "0." + sa[1];
		double d1 = Float.parseFloat(s2);
		d1 *= 12;
		d1 = Math.floor(d1);
		s2 = String.valueOf(d1);
		if(s2.contains(".0"))
			s2 = s2.replace(".0", "");
		s = new StringBuilder(s1).append("\'").append(s2).append("\"").toString();
		return s;
	}
	
	public String getDisplayHeightMeters()
	{
		if(height == 0.0 || name == "???")
			return "??? m";
		return height + " m";
	}
	
	public String getDisplayWeightPounds()
	{
		double d = weight * 2.20462262;
		d = new BigDecimal(d).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		String s = String.valueOf(d);
		if(weight == 0.0 || name == "???")
			s = "???";
		s += " lbs";
		return s;
	}
	
	public String getDisplayWeightKilos()
	{
		String s = String.valueOf(weight);
		if(weight == 0.0|| name == "???")
			s = "???";
		s += " kg";
		return s;
	}
	
	public String getDisplayWeight(boolean b)
	{
		String s = b?getDisplayWeightKilos():getDisplayWeightPounds();
		return s;
	}
	
	public String getDisplayHeight(boolean b)
	{
		String s = b?getDisplayHeightMeters():getDisplayHeightFeet();
		return s;
	}
	
}