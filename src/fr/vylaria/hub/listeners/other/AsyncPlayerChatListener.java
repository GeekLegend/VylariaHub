package fr.vylaria.hub.listeners.other;

import fr.vylaria.api.utils.Constants;
import fr.vylaria.hub.VylariaHub;
import fr.vylaria.api.player.account.Account;
import fr.vylaria.api.player.account.Rank;
import fr.vylaria.api.player.account.MongoAccount;
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
        MongoAccount mongoAccount = VylariaHub.getInstance().getMongoAccount();
        Account account = mongoAccount.get(player.getUniqueId());
        Rank rank = account.getRank();
        String suffix = "";
        if (account.isHost()){
            suffix = Constants.HOST_SUFFIX;
        }
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
