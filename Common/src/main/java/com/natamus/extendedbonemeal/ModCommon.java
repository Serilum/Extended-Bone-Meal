package com.natamus.extendedbonemeal;


import com.natamus.collective.globalcallbacks.GlobalCropCallback;
import com.natamus.extendedbonemeal.events.ExtendedEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ModCommon {

	public static void init() {
		loadEvents();
	}

	private static void loadEvents() {
		GlobalCropCallback.ON_BONE_MEAL_APPLY.register((Player player, Level level, BlockPos blockPos, BlockState blockState, ItemStack itemStack) -> {
			return ExtendedEvent.onBoneMeal(player, level, blockPos, blockState, itemStack);
		});
	}
}