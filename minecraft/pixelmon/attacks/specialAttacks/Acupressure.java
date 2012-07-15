package pixelmon.attacks.specialAttacks;

import java.util.ArrayList;
import java.util.Random;

import pixelmon.ChatHandler;
import pixelmon.attacks.Attack;
import pixelmon.entities.PixelmonEntityHelper;

import net.minecraft.src.ModLoader;

public class Acupressure extends SpecialAttackBase {

	public Acupressure() {
		super(SpecialAttackType.Acupressure, ApplyStage.During, false);
	}

	@Override
	public boolean ApplyEffect(PixelmonEntityHelper user, PixelmonEntityHelper target, Attack a, ArrayList<String> attackList) {
		boolean handled = false;
		while (!handled) {
			int selection = (new Random()).nextInt(7);
			switch (selection) {
			case 0:
				if (user.getBattleStats().AttackModifier < 400) {
					user.getBattleStats().IncreaseAttack(2);
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Attack was raised sharply!");
					handled = true;
				}
				break;
			case 1:
				if (user.getBattleStats().DefenceModifier < 400) {
					user.getBattleStats().IncreaseDefence(2);
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Defence was raised sharply!");
					handled = true;
				}
				break;
			case 2:
				if (user.getBattleStats().SpeedModifier < 400) {
					user.getBattleStats().IncreaseSpeed(2);
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Speed was raised sharply!");
					handled = true;
				}
				break;
			case 3:
				if (user.getBattleStats().SpecialAttackModifier < 400) {
					user.getBattleStats().IncreaseSpecialAttack(2);
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Special Attack was raised sharply!");
					handled = true;
				}
				break;
			case 4:
				if (user.getBattleStats().SpecialDefenceModifier < 400) {
					user.getBattleStats().IncreaseSpecialDefence(2);
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Special Defence was raised sharply!");
					handled = true;
				}
				break;
			case 5:
				if (user.getBattleStats().Accuracy < 300) {
					user.getBattleStats().IncreaseAccuracy(2);
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Accuracy was raised sharply!");
					handled = true;
				}
				break;
			case 6:
				if (user.getBattleStats().Evasion < 300) {
					user.getBattleStats().IncreaseEvasion(2);
					ChatHandler.sendChat(user.getOwner(), target.getOwner(), "Evasion was raised sharply!");
					handled = true;
				}
				break;
			}
		}
		return false;
	}
}
