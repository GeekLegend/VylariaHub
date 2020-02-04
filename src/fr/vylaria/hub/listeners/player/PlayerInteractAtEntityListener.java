package fr.vylaria.hub.listeners.player;

import fr.vylaria.hub.VylariaHub;
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

        if (entity != null)
        {
            if (entity.getType() == EntityType.ARMOR_STAND)
            {
                event.setCancelled(true);
            }
        }
    }

}
