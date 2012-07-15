package pixelmon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pixelmon.EnumBiomes;
import pixelmon.EnumEvolutionStone;
import pixelmon.attacks.Type;
import pixelmon.entities.EvolutionInfo;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EnumCreatureType;

public class DatabaseStats {

	public static BaseStats GetBaseStats(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");

			BaseStats stats = new BaseStats();
			while (rs.next()) {
				stats.HP = rs.getInt("BaseHP");
				stats.Attack = rs.getInt("BaseAttack");
				stats.Defence = rs.getInt("BaseDefence");
				stats.Speed = rs.getInt("BaseSpeed");
				stats.SpAtt = rs.getInt("BaseSpAttack");
				stats.SpDef = rs.getInt("BaseSpDefence");
				stats.CatchRate = rs.getInt("CatchRate");
				stats.MalePercent = rs.getInt("MalePercent");
				stats.EvolveLevel = rs.getInt("EvolveLevel");
				stats.EvolveInto = rs.getString("EvolveInto");
				stats.CanFly = rs.getInt("CanFly") == 1;
				stats.Height = rs.getFloat("Height");
				stats.Type1 = Type.parseType(rs.getString("Type1"));
				stats.BaseExp = rs.getInt("BaseExp");
				stats.ExperienceGroup = ExperienceGroup.getExperienceGroup(rs.getString("ExperienceGroup"));
				stats.nationalPokedexNumber = rs.getInt("NationalPokedexNumber");
				rs.getString("Type2");
				if (!rs.wasNull())
					stats.Type2 = Type.parseType(rs.getString("Type2"));
			}
			conn.close();
			return stats;
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return null;
	}

	public static BiomeGenBase[] GetSpawnBiomes(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				BiomeGenBase[] biomes;
				String biomeString = rs.getString("SpawnBiomes");
				if (rs.wasNull())
					return null;
				String[] biomeList = biomeString.split(";");
				biomes = new BiomeGenBase[biomeList.length];
				int i = 0;
				for (String biomeName : biomeList) {
					EnumBiomes e = EnumBiomes.parseBiome(biomeName);
					biomes[i] = e.getBiome();
					i++;
				}
				return biomes;
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return null;
	}

	public static int GetRarity(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getInt("Rarity");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}

	public static int GetMinGroupSize(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getInt("MinGroupSize");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}
	
	public static int GetMaxGroupSize(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getInt("MaxGroupSize");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}

	public static EnumCreatureType GetCreatureType(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				String type = rs.getString("CreatureType");
				if (type.equalsIgnoreCase("Land")) return EnumCreatureType.creature;
				else return EnumCreatureType.waterCreature;
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return null;
	}

	public static ArrayList<EvolutionInfo> getEvolveList(String pixelmonName) {
		ArrayList<EvolutionInfo> list = new ArrayList<EvolutionInfo>();
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select EvolveStone from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				String type = rs.getString("EvolveStone");
				String[] strList = type.split(";");
				for (String s:strList){
					String[] sSplit = s.split(":");
					EvolutionInfo i = new EvolutionInfo();
					i.evolutionStone = EnumEvolutionStone.getEvolutionStone(sSplit[0]);
					i.pokemonName = sSplit[1];
					list.add(i);
				}
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return list;
	}

	public static int getNationalPokedexNumber(String pixelmonName) {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select NationalPokedexNumber from Pixelmon where Name='" + pixelmonName + "'");
			while (rs.next()) {
				return rs.getInt("NationalPokedexNumber");
			}
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		return -1;
	}
}
