package fr.geeklegend.vylaria.hub.listeners.player;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener
{

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        RedisAccount redisAccount = VylariaHub.getInstance().getRedisAccount();
        Account account = redisAccount.get(player.getUniqueId());
        Rank rank = account.getRank();
        String suffix = account.getSuffix();

        VylariaHub.getInstance().getScoreboardManager().onLogout(player);

        if (!rank.equals(Rank.PLAYER))
        {
            if (!suffix.isEmpty())
            {
                event.setQuitMessage(rank.getColor() + rank.getPrefix()
                        + player.getName() + " " + account.getSuffix() + " §6§ovient de quitter le hub !");
            } else
            {
                event.setQuitMessage(rank.getColor() + rank.getPrefix()
                        + player.getName() + " §6§ovient de quitter le hub !");
            }
        } else
        {
            event.setQuitMessage(null);
        }
    }

}
