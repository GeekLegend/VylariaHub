package fr.vylaria.hub.listeners.other;

import fr.vylaria.hub.VylariaHub;
import fr.vylaria.api.player.account.Account;
import fr.vylaria.api.player.account.Rank;
import fr.vylaria.api.player.account.MongoAccount;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener
{

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        MongoAccount mongoAccount = VylariaHub.getInstance().getMongoAccount();
        Account account = mongoAccount.get(player.getUniqueId());
        Rank rank = account.getRank();

        if (rank.equals(Rank.ADMIN)) return;
        event.setCancelled(true);
    }

}
