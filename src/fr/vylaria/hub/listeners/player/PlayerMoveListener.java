package fr.vylaria.hub.listeners.player;

import fr.vylaria.hub.VylariaHub;
import fr.vylaria.hub.manager.HubManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener
{

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = VylariaHub.getInstance().getServer().getWorlds().get(0);
        Location spawn = HubManager.SPAWN_LOCATION;

        if (!player.getGameMode().equals(GameMode.CREATIVE))
        {
            if (location.getY() <= 0)
            {
                player.teleport(spawn);
            }
        }
    }

}
