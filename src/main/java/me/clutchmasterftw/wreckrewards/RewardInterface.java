package me.clutchmasterftw.wreckrewards;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;

public interface RewardInterface {
    double getChance();

    ItemStack getItemStack();

    String getCommand();

    String getItemName();

    CustomStack getCustomStack();
}
