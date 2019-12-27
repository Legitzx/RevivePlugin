package revive.reviveplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {
    private Player target;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("revive")) {
            if(sender instanceof Player) {
                Location loc = null;
                ItemStack[] inv = null;
                ItemStack[] armor = null;
                if(sender.hasPermission("revive.admin")) {
                    Player player = (Player) sender;
                    if(args.length < 1) {
                        player.sendMessage(ChatColor.GREEN + "---------- Help: Revive----------");
                        player.sendMessage(ChatColor.YELLOW + "/revive <ign>: " + ChatColor.WHITE + " Rollback players location/inventory/armor.");
                        player.sendMessage(ChatColor.YELLOW + "/revive inv <ign>: " + ChatColor.WHITE + " Rollback players inventory.");
                        player.sendMessage(ChatColor.YELLOW + "/revive loc <ign>: " + ChatColor.WHITE + " Rollback players location.");
                        player.sendMessage(ChatColor.YELLOW + "/revive armor <ign>: " + ChatColor.WHITE + " Rollback players armor.");
                        return true;
                    }
                    if (args.length < 2) { // /revive LEGITZX
                        try {
                            Bukkit.getPlayer(args[0]).isOnline();
                            target = Bukkit.getPlayer(args[0]);
                        } catch (NullPointerException ex) {
                            player.sendMessage(ChatColor.RED + "Targeted Player is not online!");
                            return true;
                        }
                        try {
                            loc = Reviveplugin.plugin.loc.get(target);
                            inv = Reviveplugin.plugin.inv.get(target);
                            armor = Reviveplugin.plugin.armor.get(target);
                        } catch (NullPointerException ex) {
                            player.sendMessage(ChatColor.RED + "Player Location/Inventory/Armor Data not found!");
                            return true;
                        }

                        target.teleport(loc);
                        for(ItemStack i : inv) {
                            if(i != null) {
                                target.getInventory().addItem(i);
                            }
                        }
                        target.getInventory().setArmorContents(armor);
                        player.sendMessage(ChatColor.AQUA + "Revived " + target.getName());
                        target.sendMessage(ChatColor.AQUA + "Revived!");

                    } else {
                        if(args[0].equalsIgnoreCase("inv")) { // /revive inv LEGITZX
                            try {
                                Bukkit.getPlayer(args[1]).isOnline();
                                target = Bukkit.getPlayer(args[1]);
                            } catch (NullPointerException ex) {
                                player.sendMessage(ChatColor.RED + "Targeted Player is not online!");
                                return true;
                            }

                            try {
                                inv = Reviveplugin.plugin.inv.get(target);
                            } catch (NullPointerException ex) {
                                player.sendMessage(ChatColor.RED + "Player Location Data not found!");
                                return true;
                            }

                            for(ItemStack i : inv) {
                                if(i != null) {
                                    target.getInventory().addItem(i);
                                }
                            }
                            player.sendMessage(ChatColor.AQUA + "Inventory Rollback was a Success!");
                            target.sendMessage(ChatColor.AQUA + "Inventory Rollback was a Success!");
                            return true;
                        }
                        if(args[0].equalsIgnoreCase("loc")) { // /restore loc LEGITZX
                            try {
                                Bukkit.getPlayer(args[1]).isOnline();
                                target = Bukkit.getPlayer(args[1]);
                            } catch (NullPointerException ex) {
                                player.sendMessage(ChatColor.RED + "Targeted Player is not online!");
                                return true;
                            }

                            try {
                                loc = Reviveplugin.plugin.loc.get(target);
                            } catch (NullPointerException ex) {
                                player.sendMessage(ChatColor.RED + "Player Location/Inventory/Armor Data not found!");
                                return true;
                            }

                            target.teleport(loc);
                            player.sendMessage(ChatColor.AQUA + "Location Rollback was a Success!");
                            target.sendMessage(ChatColor.AQUA + "Location Rollback was a Success!");
                            return true;
                        }

                        if(args[0].equalsIgnoreCase("armor")) { // /restore loc LEGITZX
                            try {
                                Bukkit.getPlayer(args[1]).isOnline();
                                target = Bukkit.getPlayer(args[1]);
                            } catch (NullPointerException ex) {
                                player.sendMessage(ChatColor.RED + "Targeted Player is not online!");
                                return true;
                            }

                            try {
                                armor = Reviveplugin.plugin.armor.get(target);
                            } catch (NullPointerException ex) {
                                player.sendMessage(ChatColor.RED + "Armor Data not found!");
                                return true;
                            }

                            target.getInventory().setArmorContents(armor);
                            player.sendMessage(ChatColor.AQUA + "Armor Rollback was a Success!");
                            target.sendMessage(ChatColor.AQUA + "Armor Rollback was a Success!");
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
