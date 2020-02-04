package fr.vylaria.hub.manager;

import fr.vylaria.api.player.account.Account;
import fr.vylaria.api.player.account.MongoAccount;
import fr.vylaria.api.player.account.Rank;
import fr.vylaria.hub.VylariaHub;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HubManager
{

    public static final String VANISH_PREFIX = "§7[§eVisibilité§7] ";
    public static final String SPEED_PREFIX = "§7[§eVitesse§7] ";
    public static final Location SPAWN_LOCATION = new Location(Bukkit.getWorlds().get(0), -0.5D, 102,
            0.5D, 180, 0);

    public void setVanish(Player player, boolean isVanish, boolean isJoin)
    {
        if (isVanish)
        {
            for (Player players : Bukkit.getOnlinePlayers())
            {
                player.showPlayer(players);
            }
            if (!isJoin)
            {
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20, 2));
                player.sendMessage(VANISH_PREFIX + "§7Les joueurs sont désormais affichés.");
            }
        } else
        {
            for (Player players : Bukkit.getOnlinePlayers())
            {
                MongoAccount mongoAccount = VylariaHub.getInstance().getMongoAccount();
                Account account = mongoAccount.get(players.getUniqueId());
                Rank rank = account.getRank();

                if (rank.getPower() < Rank.HELPER.getPower())
                {
                    player.hidePlayer(players);
                } else
                {
                    player.showPlayer(players);
                }
            }
            if (!isJoin)
            {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 2));
                player.sendMessage(VANISH_PREFIX + "§7Les joueurs sont désormais cachés");
            }
        }
    }

    public void setSpeed(Player player, int level, boolean isJoin)
    {
        switch (level)
        {
            case 0:
                for (PotionEffect potionEffects : player.getActivePotionEffects())
                {
                    player.removePotionEffect(potionEffects.getType());
                }
                if (!isJoin) player.sendMessage(SPEED_PREFIX + "§7Votre vitesse a été réduite");
                break;
            case 3:
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, level));
                if (!isJoin) player.sendMessage(SPEED_PREFIX + "§7Votre vitesse a été accélérée");
                break;
            default:
                break;
        }
    }

}
