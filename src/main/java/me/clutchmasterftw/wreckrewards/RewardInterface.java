package me.clutchmasterftw.wreckrewards;

import org.bukkit.inventory.ItemStack;

public interface RewardInterface {
    double getChance();

    ItemStack getItemStack();

    String getCommand();

    String getItemName();
}
