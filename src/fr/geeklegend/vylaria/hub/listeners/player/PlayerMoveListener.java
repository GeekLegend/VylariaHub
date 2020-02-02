package fr.geeklegend.vylaria.hub.listeners.player;

import fr.geeklegend.vylaria.hub.VylariaHub;
import org.bukkit.Bukkit;
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
        Location spawn = new Location(world, world.getSpawnLocation().getX(), world.getSpawnLocation().getY(),
                world.getSpawnLocation().getZ(), 180, 0);

        if (!player.getGameMode().equals(GameMode.CREATIVE))
        {
            if (location.getY() <= 0)
            {
                player.teleport(spawn);
            }
        }
    }

}
