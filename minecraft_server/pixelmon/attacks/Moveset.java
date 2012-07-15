package pixelmon.attacks;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

import pixelmon.database.DatabaseMoves;

import net.minecraft.src.NBTTagCompound;

public class Moveset extends AbstractList<Attack>
implements List<Attack>, RandomAccess, Cloneable
{
	
	private Attack move1;
	private Attack move2;
	private Attack move3;
	private Attack move4;
	
	public Moveset()
	{
	}
	
	public Moveset(int index, Attack a)
	{
		this();
		move1 = a;
	}
	
	public Attack get(int index) 
	{
		switch(index)
		{
			case 0: return move1;
			case 1: return move2;
			case 2: return move3;
			case 3: return move4;
			default: return null;
		}
	}

	public boolean add(Attack a)
	{
		if (size() ==0) move1=a;
		else if (size() ==1) move2=a;
		else if (size() ==2) move3=a;
		else if (size() ==3) move4=a;
		else return false;
		return true;
	}
	
	public Attack set(int index, Attack a)
	{
		Attack a1 = get(index);
		switch(index)
		{
			case 0: 
				move1 = a;
				break;
			case 1: 
				move2 = a;
				break;
			case 2: 
				move3 = a;
				break;
			case 3: 
				move4 = a;
				break;
		}
		return a1;
	}
	
	public Attack remove(int index)
	{
		Attack a = get(index);
		set(index, null);
		return a;
	}
	
	public boolean remove(Object o)
	{
		if(!(o instanceof Attack))
			return false;
		boolean b = false;
		for(int i = 1; i < 5; i++)
		{
			b = isAt(i, (Attack) o);
			if(b)
				remove(i);
		}
		return b;
	}
	
	public int size() 
	{
		return createArrayList().size();
	}
	
	public Iterator<Attack> iterator()
	{
		return createArrayList().iterator();
	}
	
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	public boolean isAt(int index, Attack a)
	{
		if(isEmpty()) 
			return false;
		if(!contains(a))
			return false;
		switch(index)
		{
			case 0: return move1 == a;
			case 1: return move2 == a;
			case 2: return move3 == a;
			case 3: return move4 == a;
		}
		return false;
	}
	
	public boolean contains(Object o)
	{
		if(isEmpty())
			return false;
		if(!(o instanceof Attack))
			return false;
		Attack a = (Attack) o;
		return move1 == a ||
			   move2 == a ||
			   move3 == a ||
			   move4 == a;
	}
	
	public ArrayList<Attack> createArrayList()
	{
		ArrayList<Attack> list = new ArrayList<Attack>();
		if(move1 != null) list.add(move1);
		if(move2 != null) list.add(move2);
		if(move3 != null) list.add(move3);
		if(move4 != null) list.add(move4);
		return list;
	}
	
	public void clear()
	{
		move1 = null;
		move2 = null;
		move3 = null;
		move4 = null;
	}

	public void writeToNBT(NBTTagCompound var1) {
		var1.setInteger("PixelmonNumberMoves", size());
		for (int i =0; i < size(); i++){
			var1.setString("PixelmonMoveName" + i,get(i).attackName);
			var1.setBoolean("PixelmonMoveSTAB" + i,get(i).STAB);
			var1.setInteger("PixelmonMovePP" + i,get(i).pp);
			var1.setInteger("PixelmonMovePPBase" + i,get(i).ppBase);
			var1.setInteger("PixelmonMovePPMax" + i,get(i).ppmax);
		}
	}

	public void readFromNBT(NBTTagCompound var1) {
		clear();
		int numMoves = var1.getInteger("PixelmonNumberMoves");
		for (int i=0; i < numMoves; i++){
			Attack a = DatabaseMoves.getAttack(var1.getString("PixelmonMoveName" + i));
			a.STAB = var1.getBoolean("PixelmonMoveSTAB" + i);
			a.pp = var1.getInteger("PixelmonMovePP" + i);
			a.ppBase = var1.getInteger("PixelmonMovePPBase" + i);
			a.ppmax = var1.getInteger("PixelmonMovePPMax" + i);
			add(a);
		}
	}
}
	
