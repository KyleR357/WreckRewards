package me.clutchmasterftw.wreckrewards;

import org.bukkit.inventory.ItemStack;

public class ItemStackChancePair implements RewardInterface {
    private final ItemStack itemStack;
    private final double chance;

    public ItemStackChancePair(ItemStack itemStack, double chance) {
        this.itemStack = itemStack;
        this.chance = chance;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public String getCommand() {
        return null;
    }

    @Override
    public String getItemName() {
        return itemStack.getItemMeta().getDisplayName();
    }

    public double getChance() {
        return chance;
    }
}
