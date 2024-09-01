package me.clutchmasterftw.wreckrewards.events;

import com.google.common.eventbus.Subscribe;
import me.clutchmasterftw.wreckrewards.ItemStackChancePair;
import me.clutchmasterftw.wreckrewards.WreckRewards;
import org.bukkit.entity.Player;
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

    @Subscribe
    public void onPrisonMineBlockBreak(PrisonMinesBlockBreakEvent e) {
        WreckRewards.getPlugin().getLogger().info("Prison block break event.");
        List<ItemStackChancePair> rewards = new ArrayList<>();

        if(!e.isCancelled()) {
            // Event isn't canceled
            Player player = e.getPlayer();
            if(player.hasPermission("group.wrecker")) {
                rewards.add(new ItemStackChancePair(tokenItem(1), (1/2000d) * 2)); // 2x
            } else if(player.hasPermission("group.gang_leader")) {
                rewards.add(new ItemStackChancePair(tokenItem(1), (1/2000d) * 1.75)); // 1.75x
            } else if(player.hasPermission("group.supermax_security")) {
                rewards.add(new ItemStackChancePair(tokenItem(1), (1/2000d) * 1.5)); // 1.5x
            } else if(player.hasPermission("group.maximum_security")) {
                rewards.add(new ItemStackChancePair(tokenItem(1), (1/2000d) * 1.2)); // 1.2x
            } else {
                rewards.add(new ItemStackChancePair(tokenItem(1), 1/2000d)); // Default
            }

            for(int i = 0; i < rewards.size(); i++) {
                // Iterate through each item's chances
                // NOTE: This method may not necessarily be statistically fair in the sense of position checking, but it's random enough mathematically.
                Random random = new Random();
                double randomValue = random.nextDouble(); // Random double from a random seed each time executed

                player.sendMessage("Reward item: " + rewards.get(i).getItemStack().getItemMeta().getDisplayName() + ", Reward Chance: " + rewards.get(i).getChance() + ", Random hash: " + randomValue);

                if(rewards.get(i).getChance() > randomValue) {
                    // Successfully hit, prevent any other chances
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), rewards.get(i).getItemStack());

                    break;
                }
            }
        }
    }
}
