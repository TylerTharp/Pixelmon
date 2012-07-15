package pixelmon.database;

import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Pixelmon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.*;
import pixelmon.attacks.animations.AttackAnimationLeapForward;
import pixelmon.attacks.animations.AttackAnimationParser;
import pixelmon.attacks.animations.IAttackAnimation;
import pixelmon.attacks.attackEffects.EffectBase;


public class DatabaseMoves {
	public static Moveset GetInitialMoves(String pixelmonName, int level) {
		Moveset attacks = new Moveset();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select Moves from Pixelmon where Name='" + pixelmonName +"'");
			ArrayList<Move> moves = new ArrayList<Move>();
			while (rs.next()) {
				String movesString = rs.getString("Moves");
				if (rs.wasNull() && moves.size()==0) {
					attacks.add(getAttack("Tackle"));
					return attacks;
				}
				String[] movesArray = movesString.split(";");
				try{
				for (String s : movesArray){
					Move move = new Move();
					String[] moveSplits = s.split(":");
					move.level= Integer.parseInt(moveSplits[0]);
					if (move.level > level) break;
					if (moveSplits[1].startsWith("(s)")){
						move.STAB=true;
						move.moveName= moveSplits[1].substring(3, moveSplits[1].length());
					}
					else {
						move.STAB=false;
						move.moveName=moveSplits[1];
					}
					if (move.level >=0)
						moves.add(move);
				}
				}catch(Exception e){
					ChatHandler.sendChat(ModLoader.getMinecraftInstance().thePlayer,"Problem loading moves for " + pixelmonName + ". " + movesString);
				}
			}
			
			conn.close();
			
			while(moves.size()>4){
				int ind = (int)mod_Pixelmon.getRandomNumberBetween(0, moves.size()-1);
				moves.remove(ind);
			}

			for(Move m:moves){
				try{
				Attack a = getAttack(m.moveName);
				a.setSTAB(m.STAB);
				attacks.add(a);
				}catch(Exception e){
					ChatHandler.sendChat(ModLoader.getMinecraftInstance().thePlayer,"Problem loading move " + m.moveName + " for " + pixelmonName);
				}
			}
		} catch (Exception e) {
			System.out.println(pixelmonName + " has corrupted moves at level " + level);
		}
		
		return attacks;
	}

	public static boolean LearnsAttackAtLevel(String name, int level){
		try{
		Class.forName("org.sqlite.JDBC");
		Connection conn = DatabaseHelper.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select Moves from Pixelmon where Name='" + name +"'");
		while (rs.next()) {
			String movesString = rs.getString("Moves");
			if (rs.wasNull()) return false;
			String[] movesArray = movesString.split(";");
			for (String s : movesArray){
				String[] moveSplits = s.split(":");
				if (level== Integer.parseInt(moveSplits[0]))return true;
			}
		}
		conn.close();
		}catch(Exception e){}
		return false;
	}

	public static ArrayList<Attack> getAttacksAtLevel(String name,
			int level) {
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select Moves from Pixelmon where Name='" + name +"'");
			ArrayList<Move> moves = new ArrayList<Move>();
			while (rs.next()) {
				String movesString = rs.getString("Moves");
				if (rs.wasNull() && moves.size()==0) {
					return attacks;
				}
				String[] movesArray = movesString.split(";");
				for (String s : movesArray){
					Move move = new Move();
					String[] moveSplits = s.split(":");
					move.level= Integer.parseInt(moveSplits[0]);
					if (move.level == level) {
					if (moveSplits[1].startsWith("(s)")){
						move.STAB=true;
						move.moveName= moveSplits[1].substring(3, moveSplits[1].length());
					}
					else {
						move.STAB=false;
						move.moveName=moveSplits[1];
					}
					moves.add(move);
					}else if (move.level > level) break;
				}
			}
			conn.close();
			for(Move m:moves){
				Attack a = getAttack(m.moveName);
				a.setSTAB(m.STAB);
				attacks.add(a);
			}
		}catch(Exception e){}
		return attacks;
	}

	
	public static Attack getAttack(String moveName) {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Moves where Name='" + moveName +"'");
			while (rs.next()){
				ArrayList<EffectBase> effects = new ArrayList<EffectBase>();
				String[] splits = rs.getString("Effect").split(";");
				for (String e:splits) {
					EffectBase etmp =EffectBase.getEffect(e); 
					if (etmp!=null) effects.add(etmp);
				}
				String animationString = rs.getString("AttackAnimations");
				ArrayList<IAttackAnimation> animations;
				if(animationString == null){
					animations = new ArrayList<IAttackAnimation>();
					animations.add(new AttackAnimationLeapForward());
				}
				else
					animations = AttackAnimationParser.GetAnimation(animationString); 
				return new Attack(rs.getInt("MoveIndex"), rs.getString("Name"), Type.parseType(rs.getString("Type")), Attack.getAttackCategory(rs.getString("Category")),
						rs.getInt("Power"), rs.getInt("Accuracy"), rs.getInt("PP"), rs.getInt("PPMax"), false, rs.getInt("HMIndex")!=-1, effects , rs.getInt("MakesContact")==1, 
						rs.getString("Description"), animations);
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static Attack getAttack(int moveIndex) {

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DatabaseHelper.getConnection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from Moves where MoveIndex='" + moveIndex +"'");
			while (rs.next()){
				ArrayList<EffectBase> effects = new ArrayList<EffectBase>();
				String[] splits = rs.getString("Effect").split(";");
				for (String e:splits) {
					EffectBase etmp =EffectBase.getEffect(e); 
					if (etmp!=null) effects.add(etmp);
				}
				String animationString = rs.getString("AttackAnimations");
				ArrayList<IAttackAnimation> animations;
				if(animationString == null){
					animations = new ArrayList<IAttackAnimation>();
					animations.add(new AttackAnimationLeapForward());
				}
				else
					animations = AttackAnimationParser.GetAnimation(animationString); 
				return new Attack(rs.getInt("MoveIndex"), rs.getString("Name"), Type.parseType(rs.getString("Type")), Attack.getAttackCategory(rs.getString("Category")),
						rs.getInt("Power"), rs.getInt("Accuracy"), rs.getInt("PP"), rs.getInt("PPMax"), false, rs.getInt("HMIndex")!=-1, effects , rs.getInt("MakesContact")==1, 
						rs.getString("Description"), animations);
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
}
