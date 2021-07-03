package pl.barthvisuals.bvboxes.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BoxListener implements Listener {
    Random random = new Random();

    @EventHandler
    public void onPlaceBox(BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.EMERALD_ORE && e.getItemInHand().getItemMeta().hasEnchant(Enchantment.DURABILITY) && e.getItemInHand().getItemMeta().hasLore() && e.getItemInHand().getItemMeta().getDisplayName().equals("§eBOX")) {
            e.getBlockPlaced().setType(Material.AIR);
            Player p = e.getPlayer();
            Location location = e.getBlock().getLocation();
            switch (random.nextInt(5)) {
                case 0: {
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.APPLE, 15));
                    e.getPlayer().sendMessage("§8» §cWylosowałeś x15 jabłko!");
                    break;
                }
                case 1: {
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.COOKED_PORKCHOP, 5));
                    e.getPlayer().sendMessage("§8» §cWylosowałeś x5 pieczony schab!");
                    break;
                }
                case 2: {
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.DIAMOND, 5));
                    e.getPlayer().sendMessage("§8» §cWylosowałeś x5 diament!");
                    break;
                }
                case 3: {
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.DIRT, 1));
                    e.getPlayer().sendMessage("§8» §cWylosowałeś x1 gówno psa!");
                    break;
                }
                case 4: {
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.SUNFLOWER, 1));
                    e.getPlayer().sendMessage("§8» §cWylosowałeś x1 kwiatuszka :)!");
                    break;
                }
            }
        }
    }
}