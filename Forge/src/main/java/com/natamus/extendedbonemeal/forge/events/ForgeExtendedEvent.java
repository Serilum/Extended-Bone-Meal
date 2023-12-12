package com.natamus.extendedbonemeal.forge.events;

import com.natamus.extendedbonemeal.events.ExtendedEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeExtendedEvent {
	@SubscribeEvent
	public void onBoneMeal(BonemealEvent e) {
		if (!ExtendedEvent.onBoneMeal(e.getEntity(), e.getLevel(), e.getPos(), e.getBlock(), e.getStack())) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onNetherwartClick(PlayerInteractEvent.RightClickBlock e) {
		ExtendedEvent.onCropClick(e.getLevel(), e.getEntity(), e.getHand(), e.getPos(), e.getHitVec());
	}
}