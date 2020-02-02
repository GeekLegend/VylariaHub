package fr.geeklegend.vylaria.hub.listeners.player;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.armorstands.Armorstand;
import fr.geeklegend.vylaria.hub.lootbox.Lootbox;
import fr.geeklegend.vylaria.hub.manager.HubManager;
import fr.geeklegend.vylaria.hub.world.WorldManager;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import fr.vylaria.api.builders.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener
{

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        WorldManager worldManager = VylariaHub.getInstance().getWorldManager();
        Armorstand armorstand = VylariaHub.getInstance().getArmorstand();
        Lootbox lootbox = worldManager.getLootbox();
        RedisAccount redisAccount = VylariaHub.getInstance().getRedisAccount();
        Account account = redisAccount.get(player.getUniqueId());
        Rank rank = account.getRank();

        VylariaHub.getInstance().getScoreboardManager().onLogin(player);

        armorstand.sendLines(player);
        lootbox.spawnHologram(player);

        if (!rank.equals(Rank.PLAYER))
        {
            if (!account.getSuffix().isEmpty())
            {
                event.setJoinMessage(rank.getColor() + rank.getPrefix()
                        + player.getName() + " " + account.getSuffix() + " §6§ovient de rejoindre le hub !");
            } else
            {
                event.setJoinMessage(rank.getColor() + rank.getPrefix()
                        + player.getName() + " §6§ovient de rejoindre le hub !");
            }
        } else
        {
            event.setJoinMessage(null);
        }

        setup(player);
    }

    private void setup(Player player)
    {
        HubManager hubManager = VylariaHub.getInstance().getHubManager();
        RedisSetting redisSetting = VylariaHub.getInstance().getRedisSetting();
        Setting settings = redisSetting.get(player.getUniqueId());

        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.ADVENTURE);
        if (settings.isVanishEnabled()) hubManager.setVanish(player, true, true);
        if (settings.isSpeedEnabled()) hubManager.setSpeed(player, 3, true);
        player.teleport(HubManager.SPAWN_LOCATION);
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));

        player.sendMessage("");
        player.sendMessage("§eBienvenue sur Vylaria, §6" + player.getName() + " §e!");
        player.sendMessage("");
        player.sendMessage("§eTous les jeux disponibles sont accessibles via le menu principal présent sous forme de §6boussole dans votre inventaire§e.");
        player.sendMessage("");
        player.sendMessage("§f[§6§l?§f] §fVoici le moment venue, la période de §6Bêta test §fsi vous apercevez le moindre bug, veuillez nous le notifier à l'aide de la commande: §6/bug <message>§e.");
        player.sendMessage("");

        giveItems(player);
    }

    private void giveItems(Player player)
    {
        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS).setName("§6Menu principal §7§o(Clic droit)").toItemStack());
        player.getInventory().setItem(1, new ItemBuilder(Material.SKULL_ITEM).setDurability((byte) 3).setSkullOwner(player.getName()).setName("§6Mon profil §7§o(Clic droit)").toItemStack());
        player.getInventory().setItem(8, new ItemBuilder(Material.GOLD_INGOT).setName("§6Boutique §7§o(Clic droit)").toItemStack());
    }
}
