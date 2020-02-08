package fr.vylaria.hub;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.data.mongo.MongoConnection;
import fr.vylaria.api.data.mongo.MongoCredentials;
import fr.vylaria.api.player.account.MongoAccount;
import fr.vylaria.api.player.settings.MongoSetting;
import fr.vylaria.api.server.MongoServer;
import fr.vylaria.api.server.ServerStatus;
import fr.vylaria.api.utils.Utils;
import fr.vylaria.hub.commands.manager.CommandManager;
import fr.vylaria.hub.inventories.manager.InventoryManager;
import fr.vylaria.hub.listeners.manager.ListenerManager;
import fr.vylaria.hub.manager.HubManager;
import fr.vylaria.hub.scoreboard.HubScoreboard;
import fr.vylaria.hub.server.manager.ServerManager;
import fr.vylaria.hub.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class VylariaHub extends JavaPlugin
{

    public static VylariaHub instance;

    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;

    private HubManager hubManager;
    private WorldManager worldManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;
    private InventoryManager inventoryManager;
    private ServerManager serverManager;

    private MongoConnection mongoConnection;

    private MongoAccount mongoAccount;
    private MongoSetting mongoSetting;
    private MongoServer mongoServer;

    private HubScoreboard hubScoreboard;

    @Override
    public void onEnable()
    {
        instance = this;

        mongoConnection = new MongoConnection(new MongoCredentials("play.vylaria.eu", 27017, "xit1oYkEgzXnQjHr", "server", "vylaria"));
        mongoConnection.init();

        mongoAccount = new MongoAccount();
        mongoSetting = new MongoSetting();
        mongoServer = new MongoServer();

        executorMonoThread = Executors.newScheduledThreadPool(16);
        scheduledExecutorService = Executors.newScheduledThreadPool(1);

        hubManager = new HubManager();
        worldManager = new WorldManager(this);
        listenerManager = new ListenerManager(this);
        commandManager = new CommandManager(this);
        inventoryManager = new InventoryManager(this);
        serverManager = new ServerManager();

        worldManager.register();
        listenerManager.register();
        commandManager.register();
        inventoryManager.register();

        hubScoreboard = new HubScoreboard();
        hubScoreboard.register();

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            try {
                VylariaAPI.getInstance().getSocketClient().sendMessage("changeStatus-"+VylariaAPI.getInstance().getVServer().getServerName()+"-STARTED");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bukkit.getConsoleSender().sendMessage("§eChangement du statut!");
        }, 20*10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> getServerManager().updateServers(), 20 * 5L, 20L);


    }

    @Override
    public void onDisable()
    {
        instance = null;

        worldManager.getLootbox().remove();
    }

    public static VylariaHub getInstance()
    {
        return instance;
    }

    public MongoConnection getMongoConnection(){
        return mongoConnection;
    }

    public MongoAccount getMongoAccount()
    {
        return mongoAccount;
    }

    public MongoSetting getMongoSetting(){
        return mongoSetting;
    }

    public ScheduledExecutorService getExecutorMonoThread()
    {
        return executorMonoThread;
    }

    public ScheduledExecutorService getScheduledExecutorService()
    {
        return scheduledExecutorService;
    }

    public HubManager getHubManager()
    {
        return hubManager;
    }

    public InventoryManager getInventoryManager()
    {
        return inventoryManager;
    }

    public WorldManager getWorldManager()
    {
        return worldManager;
    }

    public MongoServer getMongoServer() {
        return mongoServer;
    }

    public HubScoreboard getHubScoreboard() {
        return hubScoreboard;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }
}
