package fr.geeklegend.vylaria.hub.listeners.other;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.armorstands.Armorstand;
import fr.geeklegend.vylaria.hub.inventories.MenuInventory;
import fr.geeklegend.vylaria.hub.inventories.RTFInventory;
import fr.geeklegend.vylaria.hub.inventories.UHCInventory;
import fr.geeklegend.vylaria.hub.inventories.UHCRunInventory;
import fr.geeklegend.vylaria.hub.inventories.manager.InventoryManager;
import fr.vylaria.api.interfaces.IManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SocketListener implements Listener, IManager
{

    private VylariaHub instance;

    public SocketListener(VylariaHub instance)
    {
        this.instance = instance;
    }

    @Override
    public void register()
    {
        InventoryManager inventoryManager = instance.getInventoryManager();
        MenuInventory menuInventory = inventoryManager.getMenuInventory();
        UHCInventory uhcInventory = inventoryManager.getUhcInventory();
        UHCRunInventory uhcRunInventory = inventoryManager.getUhcRunInventory();
        RTFInventory rtfInventory = inventoryManager.getRtfInventory();
        Armorstand armorstand = instance.getArmorstand();

        instance.getSocketConnection().getSocket().on("newPlayer", args -> {
            menuInventory.refresh();
            uhcInventory.refresh();
            uhcRunInventory.refresh();
            rtfInventory.refresh();

            for (Player players : Bukkit.getOnlinePlayers())
            {
                armorstand.update(players);
            }
        });

        instance.getSocketConnection().getSocket().on("playerLeft", args -> {
            menuInventory.refresh();
            uhcInventory.refresh();
            uhcRunInventory.refresh();
            rtfInventory.refresh();

            for (Player players : Bukkit.getOnlinePlayers())
            {
                armorstand.update(players);
            }
        });

        instance.getSocketConnection().getSocket().on("refreshServerStatus", args -> {
            uhcInventory.refresh();
            uhcRunInventory.refresh();
            rtfInventory.refresh();
        });
    }
}
