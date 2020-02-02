package fr.geeklegend.vylaria.hub.armorstands;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.hologram.Holograms;
import fr.vylaria.api.servers.RedisServer;
import fr.vylaria.api.servers.Server;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;

public class Armorstand
{

    private Holograms uhcHologram;
    private Holograms rtfHologram;
    private ArmorStand uhcArmorstand;
    private ArmorStand rtfArmorstand;

    public Armorstand()
    {
        //RedisServer redisServer = VylariaHub.getInstance().getRedisServer();
       // Server uhcServer = redisServer.get("UHC");
        //Server rushTheFlagServer = redisServer.get("RushTheFlag");
        World world = Bukkit.getWorlds().get(0);

        this.uhcHologram = new Holograms("§6§lUHC", "§7§o(Clic droit)");
        this.rtfHologram = new Holograms("§d§lRUSH THE FLAG", "§7§o(Clic droit)");

        //this.uhcHologram = new Holograms("§6§lUHC", "§7Il y a §e§l" + uhcServer.getOnline() + " §7joueurs sur ce jeu !", "§7§o(Clic droit)");
        //this.rtfHologram = new Holograms("§d§lRUSH THE FLAG", "§7Il y a §e§l" + rushTheFlagServer.getOnline() + " §7joueurs sur ce jeu !", "§7§o(Clic droit)");
        uhcArmorstand = (ArmorStand) world.spawnEntity(new Location(world, -6.5, 99.0, 7.5, 45, 0), EntityType.ARMOR_STAND);
        rtfArmorstand = (ArmorStand) world.spawnEntity(new Location(world, -6.5, 99.0, -6.5, 135, 0), EntityType.ARMOR_STAND);
        uhcArmorstand.setCustomName("UHC");
        uhcArmorstand.setHelmet(new ItemBuilder(Material.SKULL_ITEM).setDurability((byte) 3).setSkullOwner("AerionGames").toItemStack());
        uhcArmorstand.setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack());
        uhcArmorstand.setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).toItemStack());
        uhcArmorstand.setBoots(new ItemBuilder(Material.IRON_BOOTS).toItemStack());
        uhcArmorstand.setBasePlate(true);
        uhcArmorstand.setBodyPose(new EulerAngle(Math.toRadians(0), Math.toRadians(90), Math.toRadians(0)));
        uhcArmorstand.setItemInHand(new ItemBuilder(Material.GOLDEN_APPLE).toItemStack());
        uhcArmorstand.setArms(true);

        rtfArmorstand.setCustomName("RTF");
        rtfArmorstand.setHelmet(new ItemBuilder(Material.SKULL_ITEM).setDurability((byte) 3).setSkullOwner("Quatis").toItemStack());
        rtfArmorstand.setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setGlowing().toItemStack());
        rtfArmorstand.setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setGlowing().toItemStack());
        rtfArmorstand.setBoots(new ItemBuilder(Material.LEATHER_LEGGINGS).setGlowing().toItemStack());
        rtfArmorstand.setBasePlate(true);
        rtfArmorstand.setBodyPose(new EulerAngle(Math.toRadians(0), Math.toRadians(90), Math.toRadians(0)));
        rtfArmorstand.setItemInHand(new ItemBuilder(Material.SANDSTONE).toItemStack());
        rtfArmorstand.setArms(true);

        uhcHologram.generateLines(uhcArmorstand.getLocation().add(0.0, 2.2, 0.0));
        rtfHologram.generateLines(rtfArmorstand.getLocation().add(0.0, 2.2, 0.0));

    }

    public void update(Player player)
    {
    }

    public void removeHolograms()
    {
        uhcHologram.fullDestroy();
        rtfHologram.fullDestroy();
    }

    public void sendLines(Player player)
    {
        uhcHologram.sendLines(player);
        rtfHologram.sendLines(player);
    }

    public void remove()
    {
        uhcArmorstand.remove();
        rtfArmorstand.remove();

        removeHolograms();
    }

    public ArmorStand getUhcArmorstand()
    {
        return uhcArmorstand;
    }

    public Holograms getUhcHologram()
    {
        return uhcHologram;
    }

    public ArmorStand getRtfArmorstand()
    {
        return rtfArmorstand;
    }

    public Holograms getRtfHologram()
    {
        return rtfHologram;
    }
}
