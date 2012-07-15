package pixelmon.attacks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import pixelmon.ChatHandler;
import pixelmon.attacks.animations.AttackAnimationParser;
import pixelmon.attacks.animations.IAttackAnimation;
import pixelmon.attacks.attackEffects.EffectBase;
import pixelmon.attacks.attackEffects.EffectBase.ApplyStage;
import pixelmon.attacks.attackModifiers.AttackModifierBase;
import pixelmon.attacks.attackModifiers.AttackModifierType;
import pixelmon.attacks.specialAttacks.MultiTurnSpecialAttackBase;
import pixelmon.attacks.statusEffects.StatusEffectBase;
import pixelmon.attacks.statusEffects.StatusEffectType;
import pixelmon.entities.BaseEntityPixelmon;
import pixelmon.entities.IHaveHelper;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.*;

public class Attack {
	public static final float EFFECTIVE_NORMAL = 1, EFFECTIVE_SUPER = 2, EFFECTIVE_MAX = 4, EFFECTIVE_NOT = 0.5F, EFFECTIVE_BARELY = 0.25F, EFFECTIVE_NONE = 0;
	public static final int TYPE_NORMAL = 0, TYPE_FIRE = 1, TYPE_WATER = 2, TYPE_ELECTRIC = 3, TYPE_GRASS = 4, TYPE_ICE = 5, TYPE_FIGHT = 6, TYPE_POISON = 7,
			TYPE_GROUND = 8, TYPE_FLYING = 9, TYPE_PSYCHIC = 10, TYPE_BUG = 11, TYPE_ROCK = 12, TYPE_GHOST = 13, TYPE_DRAGON = 14, TYPE_DARK = 15,
			TYPE_STEEL = 16;
	public static final int ATTACK_PHYSICAL = 0, ATTACK_SPECIAL = 1, ATTACK_STATUS = 2;

	public Type attackType;
	public String attackName;
	public boolean STAB; // Same type attack bonus
	public int attackCategory;
	public int basePower;
	public int pp;
	public int ppBase;
	int ppmax;
	String description;
	public int accuracy;
	public ArrayList<EffectBase> effects;
	boolean makesContact;
	public int attackIndex;
	boolean isHM;
	public ArrayList<IAttackAnimation> animations = new ArrayList<IAttackAnimation>();

	public Attack(int attackIndex, String attackName, Type attackType, int attackCategory, int basePower, int accuracy, int pp, int ppmax, boolean STAB, boolean isHM,
			ArrayList<EffectBase> effects, boolean makesContact, String description, ArrayList<IAttackAnimation> animations) {
		this.attackIndex = attackIndex;
		this.attackType = attackType;
		this.attackCategory = attackCategory;
		this.attackName = attackName;
		this.basePower = basePower;
		this.accuracy = accuracy;
		this.STAB = STAB;
		this.pp = pp;
		this.ppBase = pp;
		this.ppmax = ppmax;
		this.isHM = isHM;
		this.effects = effects;
		this.makesContact = makesContact;
		this.description = description;
		this.animations = animations;
	}

	public boolean flinched = false;

	public void use(PixelmonEntityHelper user, PixelmonEntityHelper target, ArrayList<String> attackList) {
		boolean attackHandled = false, cantMiss = false;
		flinched = false;
		user.getLookHelper().setLookPositionWithEntity((Entity) target.getEntity(), 0, 0);
		double accuracy = ((double) this.accuracy) * ((double) user.getBattleStats().Accuracy) / ((double) target.getBattleStats().Evasion);
		double crit = calcCriticalHit(null);
		/* Check for Protect */
		for (StatusEffectBase e : target.getStatus()) {
			if (e.stopsIncomingAttack(user, target, this))
				return;
		}
		for (StatusEffectBase e : user.getStatus()) {
			if (!e.canAttackThisTurn(user, target))
				return;
		}

		if (this.accuracy == -1)
			cantMiss = true;
		for (EffectBase e : effects) {
			if (e.hasSpecialAccuracyEffect())
				accuracy = e.getAccuracy(user, target);
		}

		if (cantMiss || mod_Pixelmon.getRandomNumberBetween(0, 100) <= accuracy) {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " used " + attackName + " on " + target.getName() + "!");
			for (int j = 0; j < effects.size(); j++) {
				EffectBase e = effects.get(j);
				if (e.effectType == EffectType.Stats) {
					e.ApplyEffect(user, target, attackList);
				} else if (e.effectType == EffectType.Status) {
					if (target.getStatus().size() > 0) {
						for (int i = 0; i < target.getStatus().size(); i++) {
							StatusEffectBase et = target.getStatus().get(i);
							if (!et.stopsStatusChange())
								e.ApplyEffect(user, target, attackList);
						}
					} else
						e.ApplyEffect(user, target, attackList);
				}
				if (e.effectType == EffectType.AttackModifier) {
				}
			}
			for (EffectBase e : effects) {
				if (e.applyStage == ApplyStage.During) {
					if (e.effectType == EffectType.AttackModifier) {
						if (((AttackModifierBase) e).type == AttackModifierType.CriticalHit)
							crit = calcCriticalHit(e);
						else
							attackHandled = ((AttackModifierBase) e).ApplyEffect(user, target, this);
					}
				}

			}
			if (!attackHandled) {
				int power = doDamageCalc(user, target, crit);
				if (attackCategory == ATTACK_STATUS)
					power = 0;
				else {
					target.attackEntityFrom(DamageSource.causeMobDamage((EntityLiving) user.getEntity()), power);
				}

				doMove((EntityLiving) user.getEntity(), (EntityLiving) target.getEntity());

				String s = null;
				if (attackCategory != ATTACK_STATUS) {
					if (crit > 1)
						ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Critical Hit!");
					float effectiveness = Type.getTotalEffectiveness(target.getType(), attackType);
					if (effectiveness == EFFECTIVE_NONE)
						s = "It had no effect!";
					if (effectiveness == EFFECTIVE_NOT || effectiveness == EFFECTIVE_BARELY)
						s = "It wasn't very effective...";
					if (effectiveness == EFFECTIVE_SUPER || effectiveness == EFFECTIVE_MAX)
						s = "It's super effective!";
					if (s != null)
						ChatHandler.sendChat(user.getOwner(), target.getOwner(), s);
				}
			}

			for (EffectBase e : effects) {
				if (e.applyStage == ApplyStage.End) {
					if (e.effectType == EffectType.AttackModifier) {
						if (((AttackModifierBase) e).type == AttackModifierType.Flinch)
							flinched = ((AttackModifierBase) e).ApplyEffect(user, target, this);
					} else if (e.effectType == EffectType.Remove)
						e.ApplyEffect(user, target, attackList);
				}
			}
		} else {
			ChatHandler.sendChat(user.getOwner(), target.getOwner(), user.getName() + " tried to use " + attackName + ", but it missed!");
			for (EffectBase e : effects)
				e.ApplyMissEffect(user, target);
		}
		if (user.getOwner()!=null)
			mod_Pixelmon.pokeballManager.getPlayerStorage(user.getOwner()).updateNBT(user);
		if (target.getOwner()!=null)
			mod_Pixelmon.pokeballManager.getPlayerStorage(target.getOwner()).updateNBT(target);
		if (user.getTrainer()!=null) user.getTrainer().pokemonStorage.updateNBT(user);
		if (target.getTrainer()!=null) target.getTrainer().pokemonStorage.updateNBT(target);
		pp --;
		return;
	}

	public void doMove(EntityLiving user, EntityLiving target) {
		for (IAttackAnimation anim : animations)
			anim.doMove(user, target);
	}

	public int doDamageCalc(PixelmonEntityHelper user, PixelmonEntityHelper target, double crit) {
		double stab = 1;
		if (STAB)
			stab = 1.5;
		double type = Type.getTotalEffectiveness(target.getType(), attackType);
		double critical = crit;
		double rand = ((double) mod_Pixelmon.getRandomNumberBetween(85, 100)) / 100;
		double modifier = stab * type * critical * rand;
		double attack = 0, defence = 0;
		if (attackCategory == ATTACK_PHYSICAL) {
			attack = ((double) user.getStats().Attack) * ((double) user.getBattleStats().AttackModifier) / 100;
			defence = ((double) target.getStats().Defence) * ((double) target.getBattleStats().DefenceModifier) / 100;
		} else if (attackCategory == ATTACK_SPECIAL) {
			attack = ((double) user.getStats().SpecialAttack) * ((double) user.getBattleStats().SpecialAttackModifier) / 100;
			defence = ((double) target.getStats().SpecialDefence) * ((double) target.getBattleStats().SpecialDefenceModifier) / 100;
		}
		double Damage = ((2 * ((float) user.getLvl().getLevel()) + 10) / 250 * (attack / defence) * basePower + 2) * modifier;

		for (StatusEffectBase e : target.getStatus())
			Damage = e.adjustDamage(this, Damage, user, target, crit);
		return (int) Math.floor(Damage);
	}

	public double calcCriticalHit(EffectBase e) {
		int critStage = 1;
		int percent = 6;
		if (e != null) {
			if (e.effectType == EffectType.AttackModifier)
				if (((AttackModifierBase) e).type == AttackModifierType.CriticalHit)
					critStage += e.value;

			if (critStage == 1)
				percent = 6;
			else if (critStage == 2)
				percent = 13;
			else if (critStage == 3)
				percent = 25;
			else if (critStage == 4)
				percent = 33;
			else if (critStage == 5)
				percent = 50;
		}
		if (mod_Pixelmon.getRandomNumberBetween(0, 100) < percent)
			return 2;

		return 1;
	}

	public boolean canHit(PixelmonEntityHelper pixelmon1, PixelmonEntityHelper pixelmon2) {
		if (pixelmon2 == null) {
			return false;
		}

		if (((EntityLiving) pixelmon1.getEntity()).isDead || pixelmon1.getIsFainted() || ((EntityLiving) pixelmon2.getEntity()).isDead || pixelmon2.getIsFainted()) {
			return false;
		}

		return true;
	}

	public static boolean canMovesHit(PixelmonEntityHelper entity, PixelmonEntityHelper target) {
		Iterator<Attack> i = entity.getMoveset().iterator();
		boolean[] b = new boolean[4];
		int i1 = 0;
		b[0] = b[1] = b[2] = b[3] = true;
		while (i.hasNext()) {
			Attack a = i.next();
			if (!a.canHit(entity, target)) {
				b[i1] = false;
			}
			i1++;
		}
		if (!(b[0] && b[1] && b[2] && b[3]))
			return false;

		return true;
	}

	public static Attack getWhichMoveIsBest(List<Attack> moves, ArrayList<Type> types, PixelmonEntityHelper pixelmon1, PixelmonEntityHelper pixelmon2) {
		int i1 = 0;
		// for (int i = 0; i < moves.size(); i++) {
		// float f = Type
		// .getTotalEffectiveness(types, moves.get(i).attackType);
		// if (f > f1 && moves.get(i).canHit(pixelmon1, pixelmon2)) {
		// i1 = i;
		// f1 = f;
		// }
		// }
		Random r = new Random();
		i1 = r.nextInt(moves.size());
		return moves.get(i1);
	}

	public void setSTAB(boolean STAB) {
		this.STAB = STAB;
	}

	public static int getAttackCategory(String categoryString) {
		if (categoryString.equalsIgnoreCase("Special"))
			return ATTACK_SPECIAL;
		else if (categoryString.equalsIgnoreCase("Physical"))
			return ATTACK_PHYSICAL;
		else if (categoryString.equalsIgnoreCase("Status"))
			return ATTACK_STATUS;
		else {
			System.out.println("Unknown Attack Category: " + categoryString);
			return -1;
		}
	}

	public boolean doesPersist(PixelmonEntityHelper pixelmon1) {
		if (attackName.equalsIgnoreCase("Fly") || attackName.equalsIgnoreCase("Bounce")) {
			for (StatusEffectBase s : pixelmon1.getStatus())
				if (s.type == StatusEffectType.Flying)
					return true;
			return false;
		}
		for (EffectBase e : effects) {
			if (e.persists)
				return true;
		}
		return false;
	}

	public boolean cantMiss() {
		for (EffectBase e : effects) {
			if (e instanceof MultiTurnSpecialAttackBase)
				if (((MultiTurnSpecialAttackBase) e).cantMiss())
					return true;
		}
		return false;
	}
}