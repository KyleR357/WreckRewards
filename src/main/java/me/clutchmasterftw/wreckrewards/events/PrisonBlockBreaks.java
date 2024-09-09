package me.clutchmasterftw.wreckrewards.events;

import com.google.common.eventbus.Subscribe;
import me.clutchmasterftw.wreckrewards.CommandChancePair;
import me.clutchmasterftw.wreckrewards.ItemStackChancePair;
import me.clutchmasterftw.wreckrewards.RewardInterface;
import me.clutchmasterftw.wreckrewards.WreckRewards;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tech.mcprison.prison.spigot.api.PrisonMinesBlockBreakEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static me.clutchmasterftw.physicaltokens.utilities.utilities.tokenItem;

public class PrisonBlockBreaks implements Listener {
//    private Map<ItemStack, Double> rewards = new HashMap<ItemStack, Double>() {
//        {}//Empty
//    };

    @EventHandler
    public void onPrisonMineBlockBreak(PrisonMinesBlockBreakEvent e) {
        List<RewardInterface> rewards = new ArrayList<>();

        if(!e.isCancelled()) {
            // Event isn't canceled
            Player player = e.getPlayer();
            if(player.hasPermission("group.wrecker")) {
                rewards.add(new ItemStackChancePair(tokenItem(1), (1/1000d) * 2)); // 2x
            } else if(player.hasPermission("group.gang_leader")) {
                rewards.add(new ItemStackChancePair(tokenItem(1), (1/1000d) * 1.75)); // 1.75x
            } else if(player.hasPermission("group.supermax_security")) {
                rewards.add(new ItemStackChancePair(tokenItem(1), (1/1000d) * 1.5)); // 1.5x
            } else if(player.hasPermission("group.maximum_security")) {
                rewards.add(new ItemStackChancePair(tokenItem(1), (1/1000d) * 1.2)); // 1.2x
            } else {
                rewards.add(new ItemStackChancePair(tokenItem(1), 1/1000d)); // Default
            }

            // Additional rewards (hard coded in for now)
            rewards.add(new CommandChancePair("crates key give {player} minereward 1 -s", ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "MineReward" + ChatColor.WHITE + " Crate Key", 1/400d));

            for(int i = 0; i < rewards.size(); i++) {
                // Iterate through each item's chances
                // NOTE: This method may not necessarily be statistically fair in the sense of position checking, but it's random enough mathematically.
                Random random = new Random();
                double randomValue = random.nextDouble(); // Random double from a random seed each time executed

//                player.sendMessage("Reward item: " + rewards.get(i).getItemName() + ChatColor.WHITE + ", Reward Chance: " + rewards.get(i).getChance() + ", Random hash: " + randomValue);

                if(rewards.get(i).getChance() > randomValue) {
                    // Successfully hit, prevent any other chances
                    player.sendMessage(WreckRewards.PREFIX + "You found a " + rewards.get(i).getItemName() + ChatColor.WHITE + " while mining!");

                    player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 0.5f, 0.5f);

                    if(rewards.get(i) instanceof ItemStackChancePair) {
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), rewards.get(i).getItemStack());
                    } else if(rewards.get(i) instanceof CommandChancePair) {
                        String command = rewards.get(i).getCommand();
                        //[12:51:46 INFO]: command executed.
                        //[12:51:46 INFO]: 9Wreck1MC#d4d9d8 » #fd5e5ePlayer not found.
                        command = command.replace("{player}", player.getName());
                        WreckRewards.getPlugin().getLogger().info("command: " + command + " | executed for " + player.getName() + ".");
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
                    }

                    break;
                }
            }
        }
    }
}
