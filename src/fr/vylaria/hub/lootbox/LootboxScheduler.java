package fr.vylaria.hub.lootbox;

import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.interfaces.IScheduler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class LootboxScheduler extends BukkitRunnable implements IScheduler
{

    private int timer;
    private Inventory inventory;
    private Random random;
    private Player player;

    public LootboxScheduler(Inventory inventory, Player player)
    {
        this.timer = 0;
        this.inventory = inventory;
        this.random = new Random();
        this.player = player;
    }

    @Override
    public void run()
    {
        timer++;

        for (int i = 0; i < inventory.getSize(); i++)
        {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) random.nextInt(15)).setName(" ").toItemStack());
        }

        int i = random.nextInt(3);
        Sound randomSound = Sound.NOTE_BASS;

        switch (i)
        {
            case 0:
                randomSound = Sound.NOTE_PLING;
                break;
            case 1:
                randomSound = Sound.NOTE_PIANO;
                break;
            case 2:
                randomSound = Sound.CLICK;
                break;
            default:
                break;
        }

        player.playSound(player.getLocation(), randomSound, 20, 20);

        if (timer >= 10)
        {
            stop();

            player.closeInventory();
            player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 20, 20);
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 20, 20);

            for (int i2 = 0; i2 < inventory.getSize(); i2++)
            {
                inventory.setItem(i2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((byte) 1).setName(" ").toItemStack());
            }
        }
    }

    @Override
    public void stop()
    {
        cancel();
    }

    @Override
    public void reset()
    {

    }
}
