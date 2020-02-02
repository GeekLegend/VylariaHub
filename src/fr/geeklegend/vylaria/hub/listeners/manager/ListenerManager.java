package fr.geeklegend.vylaria.hub.listeners.manager;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.listeners.other.*;
import fr.geeklegend.vylaria.hub.listeners.player.*;
import fr.vylaria.api.interfaces.IManager;
import org.bukkit.plugin.PluginManager;

public class ListenerManager implements IManager
{

    private VylariaHub instance;

    public ListenerManager(VylariaHub instance)
    {
        this.instance = instance;
    }

    @Override
    public void register()
    {
        PluginManager pluginManager = instance.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), instance);
        pluginManager.registerEvents(new PlayerQuitListener(), instance);
        pluginManager.registerEvents(new PlayerMoveListener(), instance);
        pluginManager.registerEvents(new PlayerDropItemListener(), instance);
        pluginManager.registerEvents(new PlayerInteractListener(), instance);
        pluginManager.registerEvents(new PlayerInteractAtEntityListener(), instance);

        pluginManager.registerEvents(new FoodLevelChangeListener(), instance);
        pluginManager.registerEvents(new EntityDamageListener(), instance);
        pluginManager.registerEvents(new AsyncPlayerChatListener(), instance);
        pluginManager.registerEvents(new WeatherChangeListener(), instance);
        pluginManager.registerEvents(new EntityExplodeListener(), instance);
        pluginManager.registerEvents(new BlockBreakListener(), instance);
        pluginManager.registerEvents(new BlockPlaceListener(), instance);
        pluginManager.registerEvents(new InventoryClickListener(), instance);
    }
}
