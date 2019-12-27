package revive.reviveplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        if(e.getEntity() instanceof Player) {
            Player player = e.getEntity();
            if(Reviveplugin.plugin.inv.containsKey(player)) {
                Reviveplugin.plugin.inv.remove(player);
            }
            if(Reviveplugin.plugin.loc.containsKey(player)) {
                Reviveplugin.plugin.loc.remove(player);
            }

            ItemStack[] content = e.getEntity().getInventory().getContents();

            Reviveplugin.plugin.loc.put(player, player.getLocation());
            Reviveplugin.plugin.inv.put(player, content);
            Reviveplugin.plugin.armor.put(player, player.getInventory().getArmorContents());
        }
     }
}
