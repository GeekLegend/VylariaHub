package fr.geeklegend.vylaria.hub.commands.manager;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.commands.LootboxCommand;
import fr.vylaria.api.interfaces.IManager;

public class CommandManager implements IManager
{

    private VylariaHub instance;

    public CommandManager(VylariaHub instance)
    {
        this.instance = instance;
    }

    @Override
    public void register()
    {
        instance.getCommand("lootbox").setExecutor(new LootboxCommand());
    }

}
