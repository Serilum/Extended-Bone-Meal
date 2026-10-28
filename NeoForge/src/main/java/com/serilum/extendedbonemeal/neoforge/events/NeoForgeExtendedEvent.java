package com.serilum.extendedbonemeal.neoforge.events;

import com.serilum.extendedbonemeal.events.ExtendedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class NeoForgeExtendedEvent {
	@SubscribeEvent
	public static void onNetherwartClick(PlayerInteractEvent.RightClickBlock e) {
		ExtendedEvent.onCropClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}