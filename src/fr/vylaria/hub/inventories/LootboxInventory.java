package fr.vylaria.hub.inventories;

import fr.vylaria.api.inventory.AbstractInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class LootboxInventory extends AbstractInventory implements Listener
{
    public LootboxInventory()
    {
        super(5 * 9, "Lootbox");
    }

    @Override
    public Inventory create(Player player)
    {
        return inventory;
    }
}
