package com.vencillio.rs2.content.combat.special.specials;

import com.vencillio.rs2.content.combat.special.Special;
import com.vencillio.rs2.entity.Animation;
import com.vencillio.rs2.entity.Graphic;
import com.vencillio.rs2.entity.player.Player;

public class DragonWarhammerSpecialAttack implements Special {

	@Override
	public boolean checkRequirements(Player player) {
		
		return true;
	}

	@Override
	public int getSpecialAmountRequired() {
		return 50;
	}

	@Override
	public void handleAttack(Player player) {
		player.getCombat().getMelee().setAnimation(new Animation(1378, 0));
		player.getUpdateFlags().sendGraphic(Graphic.highGraphic(1292, 0));
		//this is a second hit
		//player.getCombat().getMelee().execute(player.getCombat().getAttacking());
	}

}
