package fr.geeklegend.vylaria.hub.listeners.other;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener
{

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        RedisAccount redisAccount = VylariaHub.getInstance().getRedisAccount();
        Account account = redisAccount.get(player.getUniqueId());
        Rank rank = account.getRank();

        if (rank.equals(Rank.ADMIN)) return;
        event.setCancelled(true);
    }

}
