package com.serilum.extendedbonemeal.util;

import com.natamus.collective.config.DuskConfigModToggles;
import com.natamus.collective.functions.CropFunctions;
import com.natamus.collective.services.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;

public class Util {
	public static boolean isBoneMeal(ItemStack itemStack) {
		Item item = itemStack.getItem();

		if (item.equals(Items.BONE_MEAL)) {
			return true;
		}

		if (isModEnabled("kelpfertilizer")) {
			return item.equals(Items.KELP);
		}

		return false;
	}

	public static boolean isModEnabled(String modId) {
		return Services.MODLOADER.isModLoaded(modId) && !DuskConfigModToggles.isDisabledSerilumMod(modId);
	}

	public static boolean growTallCrop(Level level, BlockPos pos, Block block) {
		if (block.equals(Blocks.CACTUS)) {
			return CropFunctions.growCactus(level, pos);
		}
		else if (block.equals(Blocks.SUGAR_CANE)) {
			return CropFunctions.growSugarcane(level, pos);
		}
		else if (block.equals(Blocks.VINE)) {
			return CropFunctions.growVine(level, pos);
		}

		return false;
	}

	public static boolean growNetherWartOneStage(Level level, BlockPos pos, BlockState blockState) {
		int age = blockState.getValue(NetherWartBlock.AGE);
		if (age >= NetherWartBlock.MAX_AGE) {
			return false;
		}

		level.setBlockAndUpdate(pos, blockState.setValue(NetherWartBlock.AGE, age + 1));
		return true;
	}
}
