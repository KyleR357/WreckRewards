package me.clutchmasterftw.wreckrewards;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;

public class CustomItemStackChancePair implements RewardInterface {
    private final CustomStack customStack;
    private final double chance;

    public CustomItemStackChancePair(CustomStack customStack, double chance) {
        this.customStack = customStack;
        this.chance = chance;
    }

    public double getChance() {
        return chance;
    }

    public String getItemName() {
        return customStack.getDisplayName();
    }

    public CustomStack getCustomStack() {
        return customStack;
    }

    @Override
    public ItemStack getItemStack() {
        return customStack.getItemStack();
    }

    @Override
    public String getCommand() {
        return null;
    }
}
