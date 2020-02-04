package fr.vylaria.hub.inventories;

import fr.vylaria.hub.VylariaHub;
import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.AbstractInventory;
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

public class RTFInventory extends AbstractInventory implements Listener
{

    /*private RedisServer redisServer;
    private Server rtf1Server;*/

    public RTFInventory()
    {
        super(6 * 9, "RTF » Serveurs");
    }

    @Override
    public Inventory create(Player player)
    {
        /*redisServer = VylariaHub.getInstance().getRedisServer();
        rtf1Server = redisServer.get("RushTheFlag1");*/

        inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(4, new ItemBuilder(Material.SANDSTONE).setName("§dRTF").toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(10, new ItemBuilder(Material.SANDSTONE).setName("§dRTF #1")
                .setLore(Arrays.asList(" ", "§7Joueurs en jeu §8» §a§l"/* + rtf1Server.getOnline()*/, " ", "§eClic-gauche §8» §eRejoindre"))
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
                    case SANDSTONE:
                        if (clickType.isLeftClick())
                        {
                            if (item.getItemMeta().getDisplayName().contains("1"))
                            {
                                //VylariaAPI.getInstance().getBungeeChannelApi().connect(player, "rushtheflag1");
                            }
                        }
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
                /*redisServer = VylariaHub.getInstance().getRedisServer();
                rtf1Server = redisServer.get("RushTheFlag1");

                contents.setItem(10, new ItemBuilder(Material.SANDSTONE).setName("§dRTF #1")
                        .setLore(Arrays.asList(" ", "§7Joueurs en jeu §8» §a§l" + rtf1Server.getOnline(), " ", "§eClic-gauche §8» §eRejoindre"))
                        .toItemStack());*/
            }
        }
    }

}
