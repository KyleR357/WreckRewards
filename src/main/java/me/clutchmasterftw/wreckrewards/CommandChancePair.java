package me.clutchmasterftw.wreckrewards;

import org.bukkit.inventory.ItemStack;

public class CommandChancePair implements RewardInterface {
    private final String command;
    private final String itemName;
    private final double chance;

    public CommandChancePair(String command, String itemName, double chance) {
        this.command = command;
        this.itemName = itemName;
        this.chance = chance;
    }

    public String getCommand() {
        return command;
    }

    public String getItemName() {
        return itemName;
    }

    public double getChance() {
        return chance;
    }

    @Override
    public ItemStack getItemStack() {
        return null;
    }
}
