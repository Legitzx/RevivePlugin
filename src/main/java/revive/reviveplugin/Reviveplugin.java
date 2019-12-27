package revive.reviveplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Reviveplugin extends JavaPlugin {
    public static Reviveplugin plugin;
    public HashMap<Player, ItemStack[]> inv = new HashMap<>();
    public HashMap<Player, Location> loc = new HashMap<>();
    public HashMap<Player, ItemStack[]> armor = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Revive Plugin Enabled!");
        this.getCommand("revive").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Events(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Revive Plugin Disabled!");
    }
}
