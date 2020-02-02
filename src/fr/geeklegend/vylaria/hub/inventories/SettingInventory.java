package fr.geeklegend.vylaria.hub.inventories;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.manager.HubManager;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.account.settings.Setting;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.AbstractInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SettingInventory extends AbstractInventory implements Listener
{

    private RedisSetting redisSetting;
    private Setting settings;
    private String vanishStatus;
    private String speedStatus;

    public SettingInventory()
    {
        super(6 * 9, "Paramètres");

        this.redisSetting = VylariaHub.getInstance().getRedisSetting();
    }

    @Override
    public Inventory create(Player player)
    {
        settings = redisSetting.get(player.getUniqueId());

        if (settings.isVanishEnabled())
        {
            vanishStatus = "§aActivé";
        } else
        {
            vanishStatus = "§cDésactivé";
        }
        if (settings.isSpeedEnabled())
        {
            speedStatus = "§aActivé";
        } else
        {
            speedStatus = "§cDésactivé";
        }

        inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(4, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName("§6" + name).toItemStack());
        inventory.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
        inventory.setItem(21, new ItemBuilder(Material.WATCH).setName("§6Visibilité des joueurs").setLore(Arrays.asList("§7Status: " + vanishStatus, " ", "§7Choisir de voir ou non", "§7les autres joueurs dans", "§7les lobby.")).toItemStack());
        inventory.setItem(23, new ItemBuilder(Material.POTION).setDurability((byte) 8258).removePotionLore().setName("§6Vitesse du Lobby").setLore(Arrays.asList("§7Status: " + speedStatus, " ", "§7Choisissez le rythme de", "§7déplacement qui vous", "§7convient.")).toItemStack());
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
        inventory.setItem(size - 1, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());

        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        HubManager hubManager = VylariaHub.getInstance().getHubManager();
        settings = redisSetting.get(player.getUniqueId());

        if (inventory != null && inventory.getName().equalsIgnoreCase(name))
        {
            event.setCancelled(true);

            if (item != null)
            {
                switch (item.getType())
                {
                    case WATCH:
                        if (settings.isVanishEnabled())
                        {
                            settings.setVanishEnabled(false);
                            hubManager.setVanish(player, false, false);
                        } else
                        {
                            settings.setVanishEnabled(true);
                            hubManager.setVanish(player, true, false);
                        }
                        redisSetting.update(settings);

                        refresh();
                        break;
                    case POTION:
                        if (settings.isSpeedEnabled())
                        {
                            settings.setSpeedEnabled(false);
                            hubManager.setSpeed(player, 0, false);
                        } else
                        {
                            settings.setSpeedEnabled(true);
                            hubManager.setSpeed(player, 3, false);
                        }
                        redisSetting.update(settings);

                        refresh();
                        break;
                    case ARROW:
                        player.openInventory(new MenuInventory().create(player));
                        break;
                    default:
                        break;
                }
            }
        }
    }


    private void refresh()
    {
        for (Player players : Bukkit.getOnlinePlayers())
        {
            InventoryView inventoryView = players.getOpenInventory();
            if (inventoryView.getTitle().equalsIgnoreCase(name))
            {
                Inventory contents = inventoryView.getTopInventory();
                settings = redisSetting.get(players.getUniqueId());

                if (settings.isVanishEnabled())
                {
                    vanishStatus = "§aActivé";
                } else
                {
                    vanishStatus = "§cDésactivé";
                }

                if (settings.isSpeedEnabled())
                {
                    speedStatus = "§aActivé";
                } else
                {
                    speedStatus = "§cDésactivé";
                }

                contents.setItem(21, new ItemBuilder(Material.WATCH).setName("§6Visibilité des joueurs").setLore(Arrays.asList("§7Status: " + vanishStatus, " ", "§7Choisir de voir ou non", "§7les autres joueurs dans", "§7les lobby.")).toItemStack());
                contents.setItem(23, new ItemBuilder(Material.POTION).setDurability((byte) 8258).removePotionLore().setName("§6Vitesse du Lobby").setLore(Arrays.asList("§7Status: " + speedStatus, " ", "§7Choisissez le rythme de", "§7déplacement qui vous", "§7convient.")).toItemStack());
            }
        }
    }

}