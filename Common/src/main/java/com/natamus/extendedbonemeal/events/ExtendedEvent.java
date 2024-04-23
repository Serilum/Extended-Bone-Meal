package com.natamus.extendedbonemeal.events;

import com.natamus.collective.functions.CropFunctions;
import com.natamus.extendedbonemeal.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ExtendedEvent {
	public static boolean onBoneMeal(Player player, Level level, BlockPos clickPos, BlockState blockState, ItemStack itemStack) {
		if (level.isClientSide) {
			return true;
		}

		if (player == null) {
			return true;
		}

		if (!player.isCrouching()) {
			return true;
		}

		if (!Util.isBoneMeal(itemStack)) {
			return true;
		}

		Block block = blockState.getBlock();
		if (!(block instanceof BonemealableBlock) || block instanceof SaplingBlock || block.equals(Blocks.GRASS_BLOCK) || block instanceof TallGrassBlock || block instanceof MushroomBlock) {
			return true;
		}

		return !CropFunctions.growCrop(level, player, blockState, clickPos, itemStack);
	}

	public static boolean onCropClick(Level level, Player player, InteractionHand interactionHand, BlockPos targetPos, BlockHitResult blockHitResult) {
		if (level.isClientSide) {
			return true;
		}

		ItemStack handStack = player.getItemInHand(interactionHand);
		if (!Util.isBoneMeal(handStack)) {
			return true;
		}

		BlockState state = level.getBlockState(targetPos);
		Block block = state.getBlock();
		if (block.equals(Blocks.AIR)) {
			targetPos = targetPos.below().immutable();
			state = level.getBlockState(targetPos);
			block = state.getBlock();
		}

		if (block.equals(Blocks.NETHER_WART)) {
			return CropFunctions.growCrop(level, player, state, targetPos, handStack);
		}
		else if (block.equals(Blocks.CACTUS)) {
			if (!CropFunctions.growCactus(level, targetPos)) {
				return true;
			}
		}
		else if (block.equals(Blocks.SUGAR_CANE)) {
			if (!CropFunctions.growSugarcane(level, targetPos)) {
				return true;
			}
		}
		else if (block.equals(Blocks.VINE)) {
			if (!CropFunctions.growVine(level, targetPos)) {
				return true;
			}
		}
		else {
			return true;
		}

		if (!player.isCreative()) {
			handStack.shrink(1);
		}
		
		return true;
	}
}