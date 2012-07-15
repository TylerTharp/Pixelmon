package pixelmon.database;
import java.sql.*;

public class Test {

	public static void OpenDataBase(){
		try{
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:config/pixelmon/pixelmon.db");
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from Pixelmon");
		while (rs.next()){
			System.out.println("name = " + rs.getString("Name"));
			rs.getString("Type2");
			if (!rs.wasNull()) 
				System.out.println("type = " + rs.getString("Type1") + ", " + rs.getString("Type2"));
			else System.out.println("type = " + rs.getString("Type1"));
			System.out.println("nat pokedex no = " + rs.getInt("NationalPokedexNumber"));
			if (rs.getInt("EvolveLevel") !=-1)
				System.out.println("evolution  at lvl " + rs.getInt("EvolveLevel") + " becomes " + rs.getString("EvolveInto"));
			System.out.println(rs.getString("Description"));
		}
		
		}catch(Exception e){
			
		}
		
	}
}
