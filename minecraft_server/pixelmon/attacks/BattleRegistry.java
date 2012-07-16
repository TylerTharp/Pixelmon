package pixelmon.attacks;

import java.util.ArrayList;

import net.minecraft.src.EntityPlayer;

public class BattleRegistry {
	private ArrayList<BattleController> battleList;

	public BattleRegistry() {
		battleList = new ArrayList<BattleController>();
	}

	public void registerBattle(BattleController bc) {
		battleList.add(bc);
	}

	public int getIndex(BattleController bc) {
		for (int i = 0; i < battleList.size(); i++) {
			if (battleList.get(i) == bc)
				return i;
		}
		return -1;
	}

	public BattleController getBattle(int index) {
		return battleList.get(index);
	}

	public BattleController getBattle(EntityPlayer player) {
		for (BattleController bc : battleList) {
			if (bc.participant1 instanceof PlayerParticipant) {
				if (((PlayerParticipant) bc.participant1).player == player) {
					return bc;
				}
			} else if (bc.participant2 instanceof PlayerParticipant) {
				if (((PlayerParticipant) bc.participant2).player == player) {
					return bc;
				}
			}
		}
		return null;
	}

	public void deRegisterBattle(BattleController bc) {
		for (int i = 0; i < battleList.size(); i++) {
			if (battleList.get(i) == bc) {
				battleList.remove(i);
				return;
			}
		}
	}

	public void updateBattles() {
		for (BattleController bc : battleList) {
			bc.update();
		}
	}
}
