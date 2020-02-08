package fr.vylaria.hub.listeners.player;

import fr.vylaria.api.utils.Constants;
import fr.vylaria.hub.VylariaHub;
import fr.vylaria.api.player.account.Account;
import fr.vylaria.api.player.account.Rank;
import fr.vylaria.api.player.account.MongoAccount;
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
        MongoAccount mongoAccount = VylariaHub.getInstance().getMongoAccount();
        Account account = mongoAccount.get(player.getUniqueId());
        Rank rank = account.getRank();
        String suffix = "";
        if (account.isHost()){
            suffix = Constants.HOST_SUFFIX;
        }

        VylariaHub.getInstance().getHubScoreboard().onLeave(player);

        if (!rank.equals(Rank.PLAYER))
        {
            if (!suffix.isEmpty())
            {
                event.setQuitMessage(rank.getColor() + rank.getPrefix()
                        + player.getName() + " " + Constants.HOST_SUFFIX + " §6§ovient de quitter le hub !");
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
