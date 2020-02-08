package fr.vylaria.hub.server.manager;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.api.inventory.AbstractInventory;
import fr.vylaria.api.server.Server;
import fr.vylaria.api.server.ServerType;
import fr.vylaria.hub.VylariaHub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerManager {

    private HashMap<ServerType, List<Server>> servers;

    public ServerManager(){
        servers = new HashMap<>();
        for(ServerType st : ServerType.values()){
            servers.put(st, new ArrayList<>());
        }
    }

    public void updateServers(){
        for (List<Server> server : servers.values()){
            server.clear();
        }
        for (Server server : VylariaAPI.getInstance().getMongoServer().getAllServers()){
            servers.get(server.getServerType()).add(server);
        }

        VylariaHub.getInstance().getInventoryManager().getHubListInventory().refresh();
    }

    public HashMap<ServerType, List<Server>> getServers(){
        return servers;
    }

}
