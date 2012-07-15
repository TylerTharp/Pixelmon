package net.minecraft.src.gapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class GIO
{
	
	public static File getFile(String s)
	{
		return getFile("", s);
	}
	
	public static File getFile(String s1, String s)
	{
		File f = new File("");
		f = new File(f.getAbsolutePath() + s1, s);
		//Log.log(f.getAbsolutePath(), Log.debug);
		try {
			f.createNewFile();
		} catch (Exception e) {
			System.out.println("Can not create new File " + f.getAbsolutePath() + "\n" + e.getMessage());
		}
		return f;
	}
	
	public static void writeToFile(ArrayList<String> s, File f, boolean flag)
	{
		try 
		{
			BufferedWriter b = new BufferedWriter(new FileWriter(f.getAbsoluteFile(), flag));
			//if(flag)
				//clearFile(f);
			for(String s1 : s)
			{
				if(s1.equalsIgnoreCase("~"))
					b.newLine();
				else
					b.append(s1);
			}
			b.close();
		} catch (Exception e) 
		{
			System.out.println("Can not load File " + f.getAbsolutePath() + "\n" + e.getMessage());
		}
	}
	
	public static ArrayList<String> readFile(File f)
	{
		ArrayList<String> a = new ArrayList<String>();
		try
		{
			BufferedReader b = new BufferedReader(new FileReader(f.getAbsoluteFile()));
			String s = b.readLine();
			while(s != null)
			{
				if(!s.startsWith("#"))
					a.add(s);
				s = b.readLine();
			}
		} catch (Exception e)
		{
			System.out.println("Can not read from File " + f.getAbsolutePath() + "\n" + e.getMessage());
		}
		return a;
	}
	
	public static void clearFile(File f)
	{
		f.delete();
		try {
			f.createNewFile();
		} catch (Exception e) 
		{
		}
	}
	
}