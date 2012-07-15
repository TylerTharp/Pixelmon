package pixelmon.attacks.statusEffects;

import java.util.ArrayList;

import pixelmon.attacks.*;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.attacks.attackEffects.EffectBase.ApplyStage;
import pixelmon.entities.IHaveHelper;
import pixelmon.entities.PixelmonEntityHelper;


import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_Pixelmon;

public abstract class StatusEffectBase extends EffectBase {

	public StatusEffectType type;

	private boolean hasRepeatedEffect = false;
	private boolean hasInMoveEffect = false;
	private boolean hasPreMoveEffect = false;

	public StatusEffectBase(StatusEffectType type, boolean hasRepeatedEffect, boolean hasPreMoveEffect, boolean hasInMoveEffect) {
		super(EffectType.Status, ApplyStage.End, false);
		this.hasRepeatedEffect = hasRepeatedEffect;
		this.hasPreMoveEffect = hasPreMoveEffect;
		this.hasInMoveEffect = hasInMoveEffect;
		this.type = type;
	}

	@Override
	public abstract void ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList);

	public boolean hasRepeatedEffect() {
		return hasRepeatedEffect;
	}

	public boolean hasPreMoveEffect() {
		return hasPreMoveEffect;
	}

	public boolean hasInMoveEffect() {
		return hasInMoveEffect;
	}

	public boolean stopsSwitching() {
		return false;
	}

	public boolean ClearsOnBattleEnd(){return true;}

	public void applyRepeatedEffect(PixelmonEntityHelper pixelmon1, PixelmonEntityHelper pixelmon2){}

	public boolean canAttackThisTurn(PixelmonEntityHelper pixelmon1, PixelmonEntityHelper pixelmon2){return true;}

	public boolean stopsIncomingAttack(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a){return false;}

	public double adjustDamage(Attack a, double damage, PixelmonEntityHelper user, PixelmonEntityHelper target, double crit){return damage;}

	public boolean stopsStatusChange(){return false;}

	public void turnTick(PixelmonEntityHelper pixelmon1, PixelmonEntityHelper target){}

	public boolean cantMiss() {
		return false;
	}
}
