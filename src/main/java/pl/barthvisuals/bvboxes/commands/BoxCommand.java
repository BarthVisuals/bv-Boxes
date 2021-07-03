package pl.barthvisuals.bvboxes.commands;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BoxCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        Player p = (Player)sender;

        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(" ");
            sender.sendMessage("[bv-Boxes] Tej komendy uzywac mozna tylko z poziomu gracza! (/" + cmd.getName() + ")");
            sender.sendMessage(" ");
        } else {
            if (!(p.hasPermission("boxes.admin") || p.isOp()))
            {
                p.sendMessage("§8» §7Nie posiadasz uprawnień (§cboxes.admin§7)!");
            }
            else
            {
                if(!(strings.length > 0)){
                    p.sendMessage("§8» §7Prawidłowe użycie: /box give (nick gracza) (ilosc)");
                    p.sendMessage("§8» §7Prawidłowe użycie: /box all (ilosc)");
                }
                else
                {
                    if(strings[0].equalsIgnoreCase("all")){
                        if(strings.length == 2){
                            try{
                                if(Integer.parseInt(strings[1]) <= 0 || Double.isNaN(Double.parseDouble(strings[1])))
                                {
                                    p.sendMessage("§8» §7Podaj prawidłową liczbę!");
                                } else
                                {
                                    for(Player players : Bukkit.getOnlinePlayers()){
                                        //tutaj kod na boxy
                                        players.sendMessage("§8[§cBOXES§8] §7Cały serwer otrzymał §cx" + strings[1] + "§7 boxów!");
                                        players.sendMessage("§8[§cBOXES§8] §7Od administratora: §c" + p.getName());
                                        ItemStack stack = new ItemStack(Material.EMERALD_ORE, Integer.parseInt(strings[1]));
                                        ItemMeta meta = stack.getItemMeta();
                                        meta.setDisplayName("§eBOX");
                                        ArrayList<String> lore = new ArrayList<String>();
                                        lore.add("§cUnikatowy §eBOX");
                                        lore.add("§aPo postawieniu otrzymasz losowy przedmiot!");
                                        meta.setLore(lore);
                                        meta.addEnchant(Enchantment.DURABILITY, 69, true);

                                        stack.setItemMeta(meta);
                                        players.getInventory().addItem(stack);
                                    }
                                }
                            }catch (NumberFormatException e){ p.sendMessage("§8» §7Podaj prawidłową liczbę!"); }
                        }
                        else
                        {
                            p.sendMessage("§8» §7Prawidłowe użycie: /box all (ilosc)");
                        }
                    }
                    if(strings[0].equalsIgnoreCase("give")){
                        if(strings.length == 3){
                            Player playerGive = Bukkit.getPlayer(strings[1]);
                            if(playerGive == null) {
                                p.sendMessage("§8» §7Nie znaleziono takiego gracza!");
                            }
                            else
                            {
                                try{
                                    if(Integer.parseInt(strings[2]) <= 0 || Double.isNaN(Double.parseDouble(strings[2])))
                                    {
                                        p.sendMessage("§8» §7Podaj prawidłową liczbę!");
                                    } else
                                    {
                                        playerGive.sendMessage("§8[§cBOXES§8] §7Otrzymałeś §cx" + strings[2] + "§7 boxów!");
                                        playerGive.sendMessage("§8[§cBOXES§8] §7Od administratora: §c" + p.getName());
                                        p.sendMessage("§8[§cBOXES§8] §7Gracz: §c" + playerGive.getName() + " §7otrzymał od ciebie: §cx" + strings[2] + " §7boxów!");
                                        ItemStack stack = new ItemStack(Material.EMERALD_ORE, Integer.parseInt(strings[2]));
                                        ItemMeta meta = stack.getItemMeta();
                                        meta.setDisplayName("§eBOX");
                                        ArrayList<String> lore = new ArrayList<String>();
                                        lore.add("§cUnikatowy §eBOX");
                                        lore.add("§aPo postawieniu otrzymasz losowy przedmiot!");
                                        meta.setLore(lore);
                                        meta.addEnchant(Enchantment.DURABILITY, 69, true);

                                        stack.setItemMeta(meta);
                                        playerGive.getInventory().addItem(stack);
                                    }
                                }catch (NumberFormatException e){ p.sendMessage("§8» §7Podaj prawidłową liczbę!"); }
                            }
                        }
                        else
                        {
                            p.sendMessage("§8» §7Prawidłowe użycie: /box give (nick gracza) (ilosc)");
                        }
                    }
                }
            }
        }
        return false;
    }
}
