package fr.geeklegend.vylaria.hub.world;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.lootbox.Lootbox;
import fr.vylaria.api.interfaces.IManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class WorldManager implements IManager
{

    private VylariaHub instance;

    private Lootbox lootbox;

    public WorldManager(VylariaHub instance)
    {
        this.instance = instance;
        this.lootbox = new Lootbox(new String[] { "§e§lLootbox", "§7§o(Clic droit)" }, new Location(instance.getServer().getWorlds().get(0), 0.5, 94.0, -39.5), Material.ENDER_CHEST);
    }

    @Override
    public void register()
    {
        World world = instance.getServer().getWorlds().get(0);
        world.setTime(1000L);
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doMobSpawning", "false");

        clearEntities(world);
    }

    private void clearEntities(World world)
    {
        for (Entity entities : world.getEntities())
        {
            entities.remove();
        }
    }

    public Lootbox getLootbox()
    {
        return lootbox;
    }
}
