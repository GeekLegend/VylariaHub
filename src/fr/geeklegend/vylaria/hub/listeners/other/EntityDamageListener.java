package fr.geeklegend.vylaria.hub.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener
{

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event)
    {
        event.setCancelled(true);
    }

}
