package fr.vylaria.hub.inventories;

import fr.vylaria.hub.VylariaHub;
import fr.vylaria.hub.manager.HubManager;
import fr.vylaria.hub.world.WorldManager;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.AbstractInventory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class MenuInventory extends AbstractInventory implements Listener
{

    public MenuInventory()
    {
        super(6 * 9, "Menu principal");
    }

    @Override
    public Inventory create(Player player)
    {
        /*RedisServer redisServer = VylariaHub.getInstance().getRedisServer();
        Server UhcServer = redisServer.get("UHC");
        Server RtfServer = redisServer.get("RushTheFlag");*/

        inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(4, new ItemBuilder(player.getItemInHand().getType()).setName("§6" + name).toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(21, new ItemBuilder(Material.GOLDEN_APPLE).setName("§6§lUHC")
                .setLore(Arrays.asList("§8Genre: §6Survie Hardcore", " ", "§7Préparez votre équipement",
                        "§7dans une map vanilla et", "§7affrontez des joueurs.", " ", "§7Modes de jeu:",
                        "§8» §6UHC WTF", "§8» §6UHC Run", " ", "§7Joueurs en jeu §8» §a§l"/* + UhcServer.getOnline()*/, " ", "§eClic-gauche §8» §eTéléportation"))
                .toItemStack());
        inventory.setItem(23, new ItemBuilder(Material.WOOL).setDurability((byte) 14).setName("§d§lRTF")
                .setLore(Arrays.asList("§8Genre: §dPvP Équipe", " ", "§7Votre mission est de capturer",
                        "§7le drapeau de l'équipe", "§7adverse afin de le déposer.", "§7en dessous du votre.", " ", "§7Joueurs en jeu §8» §a§l"/* + RtfServer.getOnline()*/, " ", "§eClic-gauche §8» §eTéléportation"))
                .toItemStack());
        inventory.setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(48, new ItemBuilder(Material.BED).setName("§6Retourner au Spawn").setLore(Arrays.asList("§7Retourner au point de départ.", " ", "§eClic-gauche §8» §eRetour")).toItemStack());
        inventory.setItem(49, new ItemBuilder(Material.SKULL_ITEM).setDurability((byte) 3).setSkullOwner(player.getName()).setName("§6Mon profil").setLore(Arrays.asList("§7Visionner vos statistiques", "§7et bien d'autres...", " ", "§eClic-gauche §8» §eOuvrir")).toItemStack());
        inventory.setItem(50, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§6Paramètres").setLore(Arrays.asList("§7Régler vos paramètres", "§7comme vous le voulez !", " ", "§eClic-gauche §8» §eOuvrir")).toItemStack());
        inventory.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(size - 1, new ItemBuilder(Material.BARRIER).setName("§cFermer le menu").toItemStack());

        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        WorldManager worldManager = VylariaHub.getInstance().getWorldManager();
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ClickType clickType = event.getClick();
        ItemStack item = event.getCurrentItem();

        if (inventory != null && inventory.getName().equalsIgnoreCase(name))
        {
            event.setCancelled(true);

            if (item != null)
            {
                switch (item.getType())
                {
                    case GOLDEN_APPLE:
                        if (clickType.isLeftClick()) player.teleport(new Location(Bukkit.getWorlds().get(0), -7.5, 99.0, 8.5, -135, 0));
                        break;
                    case WOOL:
                        if (clickType.isLeftClick()) player.teleport(new Location(Bukkit.getWorlds().get(0), -7.5, 99.0, -7.5, -45, 0));
                        break;
                    case BED:
                        if (clickType.isLeftClick()) player.teleport(HubManager.SPAWN_LOCATION);
                        break;
                    case REDSTONE_COMPARATOR:
                        if (clickType.isLeftClick()) player.openInventory(VylariaHub.getInstance().getInventoryManager().getSettingInventory().create(player));
                        break;
                    case BARRIER:
                        if (clickType.isLeftClick()) player.closeInventory();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void refresh()
    {
        for (Player players : Bukkit.getOnlinePlayers())
        {
            InventoryView inventoryView = players.getOpenInventory();
            if (inventoryView.getTitle().equalsIgnoreCase(name))
            {
                Inventory contents = inventoryView.getTopInventory();
                /*RedisServer redisServer = VylariaHub.getInstance().getRedisServer();
                Server UhcServer = redisServer.get("UHC");
                Server RtfServer = redisServer.get("RushTheFlag");*/

                contents.setItem(21, new ItemBuilder(Material.GOLDEN_APPLE).setName("§6§lUHC")
                        .setLore(Arrays.asList("§8Genre: §6Survie Hardcore", " ", "§7Préparez votre équipement",
                                "§7dans une map vanilla et", "§7affrontez des joueurs.", " ", "§7Modes de jeu:",
                                "§8» §6UHC WTF", "§8» §6UHC Run", " ", "§7Joueurs en jeu §8» §a§l"/* + UhcServer.getOnline()*/, " ", "§eClic-gauche §8» §eTéléportation"))
                        .toItemStack());
                contents.setItem(23, new ItemBuilder(Material.WOOL).setDurability((byte) 14).setName("§d§lRTF")
                        .setLore(Arrays.asList("§8Genre: §dPvP Équipe", " ", "§7Votre mission est de capturer",
                                "§7le drapeau de l'équipe", "§7adverse afin de le déposer.", "§7en dessous du votre.", " ", "§7Joueurs en jeu §8» §a§l"/* + RtfServer.getOnline()*/, " ", "§eClic-gauche §8» §eTéléportation"))
                        .toItemStack());
            }
        }
    }

}
