package me.clutchmasterftw.wreckrewards;

import org.bukkit.inventory.ItemStack;

public class ItemStackChancePair {
    private ItemStack itemStack;
    private double chance;

    public ItemStackChancePair(ItemStack itemStack, double chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public double getChance() {
        return chance;
    }
}
