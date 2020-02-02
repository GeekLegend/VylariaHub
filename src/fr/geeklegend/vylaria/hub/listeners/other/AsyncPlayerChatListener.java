package fr.geeklegend.vylaria.hub.listeners.other;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener
{

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        RedisAccount redisAccount = VylariaHub.getInstance().getRedisAccount();
        Account account = redisAccount.get(player.getUniqueId());
        Rank rank = account.getRank();
        String suffix = account.getSuffix();
        String message = event.getMessage();

        if (!rank.equals(Rank.PLAYER))
        {
            if (!suffix.isEmpty())
            {
                event.setFormat(rank.getColor() + rank.getPrefix() + player.getName() + " " + suffix + " §8» §f" + message);
            } else
            {
                event.setFormat(rank.getColor() + rank.getPrefix() + player.getName() + " §8» §f" + message);
            }
        } else
        {
            if (!suffix.isEmpty())
            {
                event.setFormat(rank.getColor() + player.getName() + " " + suffix + " §8» " + rank.getColor() + message);
            } else
            {
                event.setFormat(rank.getColor() + player.getName() + " §8» " + rank.getColor() + message);
            }
        }
    }

}
