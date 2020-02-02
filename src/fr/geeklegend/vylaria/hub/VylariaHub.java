package fr.geeklegend.vylaria.hub;

import fr.geeklegend.vylaria.hub.armorstands.Armorstand;
import fr.geeklegend.vylaria.hub.commands.manager.CommandManager;
import fr.geeklegend.vylaria.hub.inventories.manager.InventoryManager;
import fr.geeklegend.vylaria.hub.listeners.manager.ListenerManager;
import fr.geeklegend.vylaria.hub.listeners.other.SocketListener;
import fr.geeklegend.vylaria.hub.manager.HubManager;
import fr.geeklegend.vylaria.hub.scoreboard.Scoreboard;
import fr.geeklegend.vylaria.hub.scoreboard.ScoreboardManager;
import fr.geeklegend.vylaria.hub.world.WorldManager;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.account.settings.RedisSetting;
import fr.vylaria.api.data.redis.RedisConnection;
import fr.vylaria.api.data.redis.RedisCredentials;
import fr.vylaria.api.servers.RedisServer;
import fr.vylaria.api.socket.SocketConnection;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class VylariaHub extends JavaPlugin
{

    public static VylariaHub instance;

    private SocketConnection socketConnection;
    private SocketListener socketListener;

    private RedisConnection redisConnection;
    private RedisAccount redisAccount;
    private RedisSetting redisSetting;
    private RedisServer redisServer;

    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;
    private ScoreboardManager scoreboardManager;

    private Armorstand armorstand;

    private HubManager hubManager;
    private WorldManager worldManager;
    private ListenerManager listenerManager;
    private CommandManager commandManager;
    private InventoryManager inventoryManager;

    private Scoreboard scoreboard;

    @Override
    public void onEnable()
    {
        instance = this;

        try
        {
            socketConnection = new SocketConnection("51.38.217.36", 8080);
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }

        redisConnection = new RedisConnection(new RedisCredentials("51.38.217.36", 6379, "bJEAc6z8TIuw7kPa", "root"), 0);
        redisConnection.init();
        redisAccount = new RedisAccount();
        redisSetting = new RedisSetting();
        redisServer = new RedisServer();

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

        armorstand = new Armorstand();

        socketListener = new SocketListener(this);
        socketListener.register();

        scoreboard = new Scoreboard();
        scoreboard.register();
    }

    @Override
    public void onDisable()
    {
        instance = null;

        redisConnection.close();

        scoreboardManager.onDisable();

        armorstand.remove();
        worldManager.getLootbox().remove();
    }

    public static VylariaHub getInstance()
    {
        return instance;
    }

    public SocketConnection getSocketConnection()
    {
        return socketConnection;
    }

    public RedisConnection getRedisConnection()
    {
        return redisConnection;
    }

    public RedisAccount getRedisAccount()
    {
        return redisAccount;
    }

    public RedisSetting getRedisSetting()
    {
        return redisSetting;
    }

    public RedisServer getRedisServer()
    {
        return redisServer;
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

    public Armorstand getArmorstand()
    {
        return armorstand;
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

}
