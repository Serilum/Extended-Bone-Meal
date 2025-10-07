package com.natamus.extendedbonemeal.forge.events;

import com.natamus.extendedbonemeal.events.ExtendedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeExtendedEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeExtendedEvent.class);

		PlayerInteractEvent.RightClickBlock.BUS.addListener(ForgeExtendedEvent::onNetherwartClick);
	}

	@SubscribeEvent
	public static void onNetherwartClick(PlayerInteractEvent.RightClickBlock e) {
		ExtendedEvent.onCropClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}