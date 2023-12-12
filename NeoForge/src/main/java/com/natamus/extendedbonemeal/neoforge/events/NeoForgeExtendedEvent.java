package com.natamus.extendedbonemeal.neoforge.events;

import com.natamus.extendedbonemeal.events.ExtendedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeExtendedEvent {
	@SubscribeEvent
	public static void onNetherwartClick(PlayerInteractEvent.RightClickBlock e) {
		ExtendedEvent.onCropClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}