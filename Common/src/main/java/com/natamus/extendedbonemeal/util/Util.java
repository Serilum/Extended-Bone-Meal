package com.natamus.extendedbonemeal.util;

import com.natamus.collective.services.Services;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class Util {
    public static boolean isBoneMeal(ItemStack itemStack) {
        Item item = itemStack.getItem();

        if (item.equals(Items.BONE_MEAL)) {
			return true;
		}

        if (Services.MODLOADER.isModLoaded("kelpfertilizer")) {
            return item.equals(Items.KELP);
        }

        return false;
    }
}
