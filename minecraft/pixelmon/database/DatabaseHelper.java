package pixelmon.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.minecraft.src.ModLoader;

/**
 * A simple little helper to handle errors and get info from SQLite
 * @author Grethen
 */
public class DatabaseHelper
{
	
	/**
	 * A check to make sure the user has the SQLite Jar, currently ignores the <code>SQLExcpetion</code> that has to do with drivers because it seems to always throw that
	 * @return True if they do, otherwise false
	 */
	public static boolean has()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			File f = new File(ModLoader.getMinecraftInstance().getMinecraftDir() + "/database/pixelmon.db").getAbsoluteFile();
			Connection c = DriverManager.getConnection("jdbc:sqlite:" + ModLoader.getMinecraftInstance().getMinecraftDir() + "/database/pixelmon.db");
			if (c ==null){
				System.out.println("Could not find the Pixelmon database at " + ModLoader.getMinecraftInstance().getMinecraftDir() + "/database/pixelmon.db");
				return false;
			}else{
				System.out.println("Found Database at " + ModLoader.getMinecraftInstance().getMinecraftDir() + "/database/pixelmon.db");
			}
			return true;
		} catch (java.lang.NoClassDefFoundError e)
		{
			System.out.println("Could not find SQLite Jar");
			return false;
		} catch (java.sql.SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find SQLite Jar");
			return false;
		}
	}
	
	
	/**
	 * Gets the connection to the path
	 * @return The connection associated with the path
	 */
	public static Connection getConnection()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:" + ModLoader.getMinecraftInstance().getMinecraftDir() + "/database/pixelmon.db");
			con.setReadOnly(true);
			return con;

		} catch (Exception e)
		{
			System.out.println("Could not get a connection to pixelmon.db");
			return null;
		}
		
	}
	
	/**
	 * Gets a <code>Statement</code> from the given <code>Connection</code>
	 * @param c - The <code>Connection</code> to use
	 * @return The <code>Statement</code> from the <code>Connection</code>
	 */
	public static Statement getStatement(Connection c)
	{
		try 
		{
			return c.createStatement();
		} catch (SQLException e) 
		{
			System.out.println("Could not create statement for database");
			return null;
		}
	}
	
	/**
	 * @return The <code>Statement</code> from the default <code>Connection</code>
	 */
	public static Statement getStatement()
	{
		return getStatement(getConnection());
	}
	
	/**
	 * Gets a <code>ResultSet</code> from the given <code>Statement</code> and query
	 * @param s - The <code>Statement</code> to use
	 * @param query - The query to look for
	 * @return The <code>ResultSet</code>
	 */
	public static ResultSet getResultSet(Statement s, String query)
	{
		try
		{
			return s.executeQuery(query);
		} catch(SQLException e)
		{
			System.out.println("Could not create ResultSet for query " + query + " for database because " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets a <code>ResultSet</code> from the default <code>Statement</code>
	 * @param query - The query to look for
	 * @return The <code>ResultSet</code>
	 */
	public static ResultSet getResultSet(String query)
	{
		return getResultSet(getStatement(), query);
	}
	
	public static void finish(Connection c, Statement s)
	{
		try 
		{
			s.close();
			c.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void finish(ResultSet r)
	{
		try
		{
			finish(r.getStatement().getConnection(), r.getStatement());
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
