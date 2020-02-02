package fr.geeklegend.vylaria.hub.inventories;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.vylaria.api.VylariaAPI;
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

public class UHCRunInventory extends AbstractInventory implements Listener
{

    private int type;
    private RedisServer redisServer;
    private Server uhcRun1Server;

    public UHCRunInventory()
    {
        super(6 * 9, "UHC Run » Serveurs");
    }

    @Override
    public Inventory create(Player player)
    {
        redisServer = VylariaHub.getInstance().getRedisServer();
        uhcRun1Server = redisServer.get("UHCRun1");
        byte statusData = 0;

        inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(3, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(5, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());

        switch (uhcRun1Server.getServerStatus())
        {
            case WAITING:
                statusData = 5;
                break;
            case IN_GAME:
            case FINISH:
            case RESTART:
            case NO_STATUS:
                statusData = 14;
                break;
            default:
                break;
        }

        switch (type)
        {
            case 1:
                inventory.setItem(10, new ItemBuilder(Material.STAINED_CLAY).setDurability(statusData).setName("§6UHC Run #1")
                        .setLore(Arrays.asList(" ", "§7État §8» " + uhcRun1Server.getServerStatus().getNameColor() + uhcRun1Server.getServerStatus().getName(), "§7Mode §8» §6Solo", " ", "§7Joueurs en jeu §8» §a§l" + uhcRun1Server.getOnline(), " ", "§eClic-gauche §8» §eRejoindre"))
                        .toItemStack());
                break;
            default:
                break;
        }

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
        inventory.setItem(size - 1, new ItemBuilder(Material.ARROW).setName("§6Retour").toItemStack());

        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
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
                    case STAINED_CLAY:
                        if (clickType.isLeftClick())
                        {
                            if (item.getItemMeta().getDisplayName().contains("1"))
                            {
                                VylariaAPI.getInstance().getBungeeChannelApi().connect(player, "uhcrun1");
                            }
                        }
                        break;
                    case ARROW:
                        if (clickType.isLeftClick()) player.openInventory(VylariaHub.getInstance().getInventoryManager().getUhcRunTypeInventory().create(player));
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
                uhcRun1Server = redisServer.get("UHCRun1");
                Inventory contents = inventoryView.getTopInventory();
                byte statusData = 0;

                switch (uhcRun1Server.getServerStatus())
                {
                    case WAITING:
                        statusData = 5;
                        break;
                    case IN_GAME:
                    case FINISH:
                    case RESTART:
                    case NO_STATUS:
                        statusData = 14;
                        break;
                    default:
                        break;
                }

                switch (type)
                {
                    case 1:
                        contents.setItem(10, new ItemBuilder(Material.STAINED_CLAY).setDurability(statusData).setName("§6UHC Run #1")
                                .setLore(Arrays.asList(" ", "§7État §8» " + uhcRun1Server.getServerStatus().getNameColor() + uhcRun1Server.getServerStatus().getName(), "§7Mode §8» §6Solo", " ", "§7Joueurs en jeu §8» §a§l" + uhcRun1Server.getOnline(), " ", "§eClic-gauche §8» §eRejoindre"))
                                .toItemStack());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void setType(int type)
    {
        this.type = type;
    }
}
