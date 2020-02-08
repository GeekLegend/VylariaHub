package fr.vylaria.hub.inventories;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.inventory.AbstractInventory;
import fr.vylaria.api.server.Server;
import fr.vylaria.api.server.ServerType;
import fr.vylaria.hub.VylariaHub;
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

import java.util.List;

public class HubListInventory extends AbstractInventory implements Listener {

    public HubListInventory()
    {
        super(6 * 9, "Liste des Hubs");
    }

    @Override
    public Inventory create(Player player)
    {

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

        serversToItems(inventory, player);

        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ClickType clickType = event.getClick();
        ItemStack item = event.getCurrentItem();

        /*if (inventory != null && inventory.getName().equalsIgnoreCase(name))
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
                                //VylariaAPI.getInstance().getBungeeChannelApi().connect(player, "uhcrun1");
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
        }*/
    }

    public void refresh()
    {
        for (Player players : Bukkit.getOnlinePlayers())
        {
            InventoryView inventoryView = players.getOpenInventory();
            if (inventoryView.getTitle().equalsIgnoreCase(name))
            {
                Inventory contents = inventoryView.getTopInventory();

                for (ItemStack item : contents.getContents()){
                    if (item != null){
                        if (item.getType() != Material.STAINED_GLASS_PANE){
                            contents.remove(item);
                        }
                    }
                }

                serversToItems(contents, players);

            }
        }
    }

    public void serversToItems(Inventory inv, Player player){
        int slot = 10;
        List<Server> servers = VylariaHub.getInstance().getServerManager().getServers().get(ServerType.HUB);

        for (Server s : servers){
            while (inv.getItem(slot) != null){
                slot++;
            }

            int percent = (s.getPlayerNamesList().size())/(s.getServerSlots())*100;
            String populationType = "";
            String populationColor = "";
            if (percent < 25){
                populationColor = "§a";
                populationType = "Faible";
            }else if (percent >= 25 && percent < 50){
                populationColor = "§e";
                populationType = "Moyenne";
            }else if (percent >= 50 && percent < 75){
                populationColor = "§c";
                populationType = "Elevée";
            }else if (percent >= 75){
                populationColor = "§4";
                populationType = "Très élevée";
            }

            String joinMessage = "§e» Cliquez pour vous connecter à ce Hub";
            if (s.getPlayerNamesList().contains(player.getDisplayName())){
                joinMessage = "§cVous êtes déjà connecté à ce Hub";
            }

            inv.setItem(slot, new ItemBuilder(Material.WOOL)
                    .setDurability(s.getServerStatus().getColor())
                    .setName("§aHub §6" + s.getServerName())
                    .setLore("§7Population du hub ("+populationColor+populationType+"§7) : "+populationColor+s.getPlayerNamesList().size(), "", joinMessage)
                    .toItemStack());
        }

    }

}
