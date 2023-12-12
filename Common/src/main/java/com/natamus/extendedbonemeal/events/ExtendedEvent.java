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
	public static boolean onBoneMeal(Player player, Level world, BlockPos cpos, BlockState state, ItemStack stack) {
		if (world.isClientSide) {
			return true;
		}

		if (player == null) {
			return true;
		}

		if (!player.isCrouching()) {
			return true;
		}
		
		ItemStack handStack = player.getItemInHand(InteractionHand.MAIN_HAND);
		if (!Util.isBoneMeal(handStack)) {
			return true;
		}
		
		Block block = state.getBlock();
		if (!(block instanceof BonemealableBlock) || block instanceof SaplingBlock || block.equals(Blocks.GRASS_BLOCK) || block instanceof TallGrassBlock || block instanceof MushroomBlock) {
			return true;
		}

		return !CropFunctions.growCrop(world, player, state, cpos);
	}
	
	public static boolean onCropClick(Level world, Player player, InteractionHand hand, BlockPos targetpos, BlockHitResult hitVec) {
		if (world.isClientSide) {
			return true;
		}
		
		ItemStack handStack = player.getItemInHand(hand);
		if (!Util.isBoneMeal(handStack)) {
			return true;
		}

		BlockState state = world.getBlockState(targetpos);
		Block block = state.getBlock();
		if (block.equals(Blocks.AIR)) {
			targetpos = targetpos.below().immutable();
			state = world.getBlockState(targetpos);
			block = state.getBlock();
		}
		
		if (block.equals(Blocks.NETHER_WART)) {
			if (!CropFunctions.growCrop(world, player, state, targetpos)) {
				return true;
			}
		}
		else if (block.equals(Blocks.CACTUS)) {
			if (!CropFunctions.growCactus(world, targetpos)) {
				return true;
			}
		}
		else if (block.equals(Blocks.SUGAR_CANE)) {
			if (!CropFunctions.growSugarcane(world, targetpos)) {
				return true;
			}
		}
		else if (block.equals(Blocks.VINE)) {
			if (!CropFunctions.growVine(world, targetpos)) {
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