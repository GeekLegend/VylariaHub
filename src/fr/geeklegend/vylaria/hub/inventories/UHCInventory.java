package fr.geeklegend.vylaria.hub.inventories;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.world.WorldManager;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.AbstractInventory;
import fr.vylaria.api.servers.RedisServer;
import fr.vylaria.api.servers.Server;
import org.bukkit.Bukkit;
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

public class UHCInventory extends AbstractInventory implements Listener
{

    private RedisServer redisServer;
    private Server uhcRunServer;

    public UHCInventory()
    {
        super(6 * 9, "UHC");
    }

    @Override
    public Inventory create(Player player)
    {
        redisServer = VylariaHub.getInstance().getRedisServer();
        uhcRunServer = redisServer.get("UHCRun");

        inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(4, new ItemBuilder(Material.GOLDEN_APPLE).setName("§6" + name).toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(22, new ItemBuilder(Material.SUGAR).setName("§6UHC Run")
                .setLore(Arrays.asList(" ", "§7Modes disponibles:",
                        "§8» §6Solo", " ", "§7Joueurs en jeu §8» §a§l" + uhcRunServer.getOnline(), " ", "§eClic-gauche §8» §eRejoindre"))
                .toItemStack());
        inventory.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(48, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(49, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(50, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
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
                    case SUGAR:
                        if (clickType.isLeftClick()) player.openInventory(VylariaHub.getInstance().getInventoryManager().getUhcRunTypeInventory().create(player));
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
                redisServer = VylariaHub.getInstance().getRedisServer();
                uhcRunServer = redisServer.get("UHCRun");
                Inventory contents = inventoryView.getTopInventory();

                contents.setItem(22, new ItemBuilder(Material.SUGAR).setName("§6UHC Run")
                        .setLore(Arrays.asList(" ", "§7Modes disponibles:",
                                "§8» §6Solo", " ", "§7Joueurs en jeu §8» §a§l" + uhcRunServer.getOnline(), " ", "§eClic-gauche §8» §eRejoindre"))
                        .toItemStack());
            }
        }
    }

}
