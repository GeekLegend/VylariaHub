package fr.geeklegend.vylaria.hub.lootbox;

import fr.vylaria.api.hologram.Holograms;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Lootbox
{

    public static final String PREFIX = "§7[§eLootbox§7] ";

    private String[] name;
    private Material material;
    private Location location;
    private Holograms hologram;
    private boolean isRemoved;

    public Lootbox(String[] name, Location location, Material material)
    {
        this.name = name;
        this.location = location;
        this.material = material;
        this.hologram = new Holograms(name);
        this.isRemoved = false;
    }

    public void remove()
    {
        location.getBlock().setType(Material.AIR);

        removeHologram();

        isRemoved = true;
    }

    public void spawnHologram(Player player)
    {
        hologram.generateLines(location.add(new Vector(0.0, 1.0, 0.0)));
        hologram.sendLines(player);
    }

    public void removeHologram(Player player)
    {
        hologram.removeLines(player);
    }

    public void removeHologram()
    {
        hologram.removeLinesForPlayers();
    }

    public String[] getName()
    {
        return name;
    }

    public void setName(String[] name)
    {
        this.name = name;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public Material getMaterial()
    {
        return material;
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

    public Holograms getHologram()
    {
        return hologram;
    }

    public void setHologram(Holograms hologram)
    {
        this.hologram = hologram;
    }

    public boolean isRemoved()
    {
        return isRemoved;
    }

    public void setRemoved(boolean removed)
    {
        isRemoved = removed;
    }
}
