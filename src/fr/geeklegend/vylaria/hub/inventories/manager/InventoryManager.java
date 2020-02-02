package fr.geeklegend.vylaria.hub.inventories.manager;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.inventories.*;
import fr.vylaria.api.interfaces.IManager;
import org.bukkit.plugin.PluginManager;

public class InventoryManager implements IManager
{

    private VylariaHub instance;

    private MenuInventory menuInventory;
    private SettingInventory settingInventory;
    private ShopInventory shopInventory;
    private UHCInventory uhcInventory;
    private UHCRunInventory uhcRunInventory;
    private UHCRunTypeInventory uhcRunTypeInventory;
    private RTFInventory rtfInventory;
    private LootboxInventory lootboxInventory;

    public InventoryManager(VylariaHub instance)
    {
        this.instance = instance;
        this.menuInventory = new MenuInventory();
        this.settingInventory = new SettingInventory();
        this.shopInventory = new ShopInventory();
        this.uhcInventory = new UHCInventory();
        this.uhcRunInventory = new UHCRunInventory();
        this.uhcRunTypeInventory = new UHCRunTypeInventory();
        this.rtfInventory = new RTFInventory();
        this.lootboxInventory = new LootboxInventory();
    }

    @Override
    public void register()
    {
        PluginManager pluginManager = instance.getServer().getPluginManager();
        pluginManager.registerEvents(menuInventory, instance);
        pluginManager.registerEvents(settingInventory, instance);
        pluginManager.registerEvents(shopInventory, instance);
        pluginManager.registerEvents(uhcInventory, instance);
        pluginManager.registerEvents(uhcRunTypeInventory, instance);
        pluginManager.registerEvents(uhcRunInventory, instance);
        pluginManager.registerEvents(rtfInventory, instance);
        pluginManager.registerEvents(lootboxInventory, instance);
    }

    public MenuInventory getMenuInventory()
    {
        return menuInventory;
    }

    public SettingInventory getSettingInventory()
    {
        return settingInventory;
    }

    public ShopInventory getShopInventory()
    {
        return shopInventory;
    }

    public UHCInventory getUhcInventory()
    {
        return uhcInventory;
    }

    public UHCRunInventory getUhcRunInventory()
    {
        return uhcRunInventory;
    }

    public UHCRunTypeInventory getUhcRunTypeInventory()
    {
        return uhcRunTypeInventory;
    }

    public RTFInventory getRtfInventory()
    {
        return rtfInventory;
    }

    public LootboxInventory getLootboxInventory()
    {
        return lootboxInventory;
    }
}
