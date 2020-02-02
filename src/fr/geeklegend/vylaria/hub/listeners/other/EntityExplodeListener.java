package fr.geeklegend.vylaria.hub.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener
{

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event)
    {
        event.setCancelled(true);
    }

}
