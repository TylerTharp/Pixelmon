package pixelmon.attacks.attackEffects;

import java.util.ArrayList;

import pixelmon.ChatHandler;
import pixelmon.attacks.EffectType;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;

public class StatsEffect extends EffectBase{
	
	private StatsEffectType type;
	private boolean isUser = false;
	
	public StatsEffect(StatsEffectType type, int value, boolean isUser) {
		super(EffectType.Stats, ApplyStage.End, false);
		this.type = type;
		this.value = value;
		this.isUser = isUser;
	}

	@Override
	public void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		PixelmonEntityHelper effected = target;
		if (isUser) effected = user;
		if (checkChance()) {
			if (type == StatsEffectType.Accuracy) {
				if (value > 0) {if (effected.getBattleStats().IncreaseAccuracy(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName()+ "'s accuracy was increased!");}
				else if (effected.getBattleStats().DecreaseAccuracy(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s accuracy was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.Evasion) {
				if (value > 0) {if (effected.getBattleStats().IncreaseEvasion(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s evasion was increased!");}
				else if (effected.getBattleStats().DecreaseEvasion(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s evasion was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");
				
			} else if (type == StatsEffectType.Attack) {
				if (value > 0) {if (effected.getBattleStats().IncreaseAttack(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s attack was increased!");}
				else if (effected.getBattleStats().DecreaseAttack(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s attack was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.Defence) {
				if (value > 0) {if (effected.getBattleStats().IncreaseDefence(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s defense was increased!");}
				else if (effected.getBattleStats().DecreaseDefence(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s defense was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.SpecialAttack) {
				if (value > 0) {if (effected.getBattleStats().IncreaseSpecialAttack(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s special attack was increased!");}
				else if (effected.getBattleStats().DecreaseSpecialAttack(Math.abs(value)))ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s special attack was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.SpecialDefence) {
				if (value > 0) {if (effected.getBattleStats().IncreaseSpecialDefence(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s special defense was increased!");}
				else if (effected.getBattleStats().DecreaseSpecialDefence(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s special defense was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			} else if (type == StatsEffectType.Speed) {
				if (value > 0) {if (effected.getBattleStats().IncreaseSpeed(value)) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s speed was increased!");}
				else if (effected.getBattleStats().DecreaseSpeed(Math.abs(value))) ChatHandler.sendChat(user.getOwner(), target.getOwner(), effected.getName() + "'s speed was decreased!");
				else ChatHandler.sendChat(user.getOwner(), target.getOwner(), "It had no effect");

			}
		}	
	}

	@Override
	public boolean cantMiss() {
		if (isUser) return true;
		return false;
	}
	
	

}
