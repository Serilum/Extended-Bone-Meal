package com.natamus.extendedbonemeal.forge.events;

import com.natamus.extendedbonemeal.events.ExtendedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeExtendedEvent {
	@SubscribeEvent
	public static void onNetherwartClick(PlayerInteractEvent.RightClickBlock e) {
		ExtendedEvent.onCropClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}