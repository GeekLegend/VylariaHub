package fr.vylaria.hub;

import fr.vylaria.api.data.mongo.MongoConnection;
import fr.vylaria.api.data.mongo.MongoCredentials;
import fr.vylaria.api.player.account.MongoAccount;
import fr.vylaria.api.player.settings.MongoSetting;
import fr.vylaria.api.server.MongoServer;
import fr.vylaria.hub.commands.manager.CommandManager;
import fr.vylaria.hub.inventories.manager.InventoryManager;
import fr.vylaria.hub.listeners.manager.ListenerManager;
import fr.vylaria.hub.manager.HubManager;
import fr.vylaria.hub.scoreboard.Scoreboard;
import fr.vylaria.hub.scoreboard.ScoreboardManager;
import fr.vylaria.hub.world.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.rmi.ServerError;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class VylariaHub extends JavaPlugin
{

    public static VylariaHub instance;

    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;
    private ScoreboardManager scoreboardManager;

    private HubManager hubManager;
    private WorldManager worldManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;
    private InventoryManager inventoryManager;

    private MongoConnection mongoConnection;

    private MongoAccount mongoAccount;
    private MongoSetting mongoSetting;
    private MongoServer mongoServer;

    private Scoreboard scoreboard;

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
        scoreboardManager = new ScoreboardManager();

        hubManager = new HubManager();
        worldManager = new WorldManager(this);
        listenerManager = new ListenerManager(this);
        commandManager = new CommandManager(this);
        inventoryManager = new InventoryManager(this);

        worldManager.register();
        listenerManager.register();
        commandManager.register();
        inventoryManager.register();

        scoreboard = new Scoreboard();
        scoreboard.register();

    }

    @Override
    public void onDisable()
    {
        instance = null;

        scoreboardManager.onDisable();

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

    public Scoreboard getScoreboard()
    {
        return scoreboard;
    }

    public ScheduledExecutorService getExecutorMonoThread()
    {
        return executorMonoThread;
    }

    public ScheduledExecutorService getScheduledExecutorService()
    {
        return scheduledExecutorService;
    }

    public ScoreboardManager getScoreboardManager()
    {
        return scoreboardManager;
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
}
