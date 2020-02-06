package fr.vylaria.hub.listeners.player;

import fr.vylaria.hub.VylariaHub;
import fr.vylaria.hub.inventories.HubListInventory;
import fr.vylaria.hub.lootbox.Lootbox;
import fr.vylaria.hub.lootbox.LootboxScheduler;
import fr.vylaria.hub.inventories.MenuInventory;
import fr.vylaria.hub.inventories.ShopInventory;
import fr.vylaria.api.builders.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener
{

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        Lootbox lootbox = VylariaHub.getInstance().getWorldManager().getLootbox();

        if (item != null)
        {
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
            {
                if (item.getType() == Material.COMPASS && item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Menu principal §7§o(Clic droit)"))
                {
                    player.openInventory(new MenuInventory().create(player));
                } else if (item.getType() == Material.GOLD_INGOT && item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Boutique §7§o(Clic droit)"))
                {
                    player.openInventory(new ShopInventory().create(player));
                }else if(item.getType() == Material.WOOL && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aHubs §7§o(Clic droit)")){
                    player.openInventory(new HubListInventory().create(player));
                }
            }
        }

        if (block != null)
        {
            if (player.isOp() && action == Action.LEFT_CLICK_BLOCK) return;

            if (block.getType() == lootbox.getMaterial())
            {
                event.setCancelled(true);

                if (action == Action.RIGHT_CLICK_BLOCK)
                {
                    if (item != null)
                    {
                        if (item.getType() == Material.TRIPWIRE_HOOK)
                        {
                            if (player.getItemInHand().getAmount() >= 2)
                            {
                                int newAmount = player.getItemInHand().getAmount() - 1;

                                player.setItemInHand(new ItemBuilder(Material.TRIPWIRE_HOOK, newAmount).setGlowing().setName(Lootbox.PREFIX + "§eClé").toItemStack());
                            } else
                            {
                                player.getInventory().remove(item);
                            }

                            Inventory lootboxInventory = VylariaHub.getInstance().getInventoryManager().getLootboxInventory().create(player);

                            player.openInventory(lootboxInventory);

                            new LootboxScheduler(lootboxInventory, player).runTaskTimer(VylariaHub.getInstance(), 0, 20);
                        }
                    } else
                    {
                        player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1.5));
                        player.playSound(player.getLocation(), Sound.FIZZ, 20, 20);
                        player.sendMessage(Lootbox.PREFIX + "§cVous n'avez pas de clé :(");
                    }
                }
            }
        }
    }
}
