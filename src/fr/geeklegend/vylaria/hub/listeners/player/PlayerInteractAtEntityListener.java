package fr.geeklegend.vylaria.hub.listeners.player;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.armorstands.Armorstand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntityListener implements Listener
{

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event)
    {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        Armorstand armorstand = VylariaHub.getInstance().getArmorstand();

        if (entity != null)
        {
            if (entity.getType() == EntityType.ARMOR_STAND)
            {
                event.setCancelled(true);

                if (entity.getCustomName().equalsIgnoreCase(armorstand.getUhcArmorstand().getCustomName()))
                {
                    player.openInventory(VylariaHub.getInstance().getInventoryManager().getUhcInventory().create(player));
                } else if (entity.getCustomName().equalsIgnoreCase(armorstand.getRtfArmorstand().getCustomName()))
                {
                    player.openInventory(VylariaHub.getInstance().getInventoryManager().getRtfInventory().create(player));
                }
            }
        }
    }

}
