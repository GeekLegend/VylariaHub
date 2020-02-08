package fr.vylaria.hub.scoreboard;

import fr.vylaria.api.interfaces.IManager;
import fr.vylaria.api.player.account.Account;
import fr.vylaria.api.player.account.MongoAccount;
import fr.vylaria.api.player.account.Rank;
import fr.vylaria.api.scoreboard.ScoreboardTeam;
import fr.vylaria.api.scoreboard.manager.ScoreboardManager;
import fr.vylaria.api.utils.Constants;
import fr.vylaria.hub.VylariaHub;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HubScoreboard extends ScoreboardManager implements IManager {

    private List<ScoreboardTeam> teams;
    private int ipLine = 2;

    public HubScoreboard() {
        super("§e§lVYLARIA", "play.vylaria.eu");

        this.teams = new ArrayList<>();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(VylariaHub.getInstance(), () -> {
            this.updateIp(ipLine);
        }, 20L, 3);
    }

    public void onJoin(Player p){
        this.addPlayer(p);
        this.createTablist(p);
    }

    public void onLeave(Player p){
        this.removePlayer(p);
    }

    public void createTablist(Player player)
    {
        for (ScoreboardTeam team : VylariaHub.getInstance().getHubScoreboard().getTeams())
        {
            ((CraftPlayer) VylariaHub.getInstance().getServer().getPlayer(player.getUniqueId())).getHandle().playerConnection.sendPacket(team.createTeam());
        }

        for (Player players1 : Bukkit.getOnlinePlayers())
        {
            for (Player players2 : Bukkit.getOnlinePlayers())
            {
                MongoAccount mongoAccount = VylariaHub.getInstance().getMongoAccount();
                Account account = mongoAccount.get(players2.getUniqueId());
                Rank rank = account.getRank();
                String suffix = "";
                if (account.isHost()){
                    suffix = Constants.HOST_SUFFIX;
                }
                ScoreboardTeam team = getScoreboardTeam("014");

                switch (rank)
                {
                    case ADMIN:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("001S");
                        } else
                        {
                            team = getScoreboardTeam("001");
                        }
                        break;
                    case RESP_DEV:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("002S");
                        } else
                        {
                            team = getScoreboardTeam("002");
                        }
                        break;
                    case RESP_BUILD:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("003S");
                        } else
                        {
                            team = getScoreboardTeam("003");
                        }
                        break;
                    case RESP_MOD:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("004S");
                        } else
                        {
                            team = getScoreboardTeam("004");
                        }
                        break;
                    case DEVELOPER:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("005S");
                        } else
                        {
                            team = getScoreboardTeam("005");
                        }
                        break;
                    case STAFF:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("006S");
                        } else
                        {
                            team = getScoreboardTeam("006");
                        }
                        break;
                    case MODERATOR:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("007S");
                        } else
                        {
                            team = getScoreboardTeam("007");
                        }
                        break;
                    case BUILDER:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("008S");
                        } else
                        {
                            team = getScoreboardTeam("008");
                        }
                        break;
                    case HELPER:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("009S");
                        } else
                        {
                            team = getScoreboardTeam("009");
                        }
                        break;
                    case PARTNER:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("010S");
                        } else
                        {
                            team = getScoreboardTeam("010");
                        }
                        break;
                    case YOUTUBER:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("011S");
                        } else
                        {
                            team = getScoreboardTeam("011");
                        }
                        break;
                    case LEGEND:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("012S");
                        } else
                        {
                            team = getScoreboardTeam("012");
                        }
                        break;
                    case VIP:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("013S");
                        } else
                        {
                            team = getScoreboardTeam("013");
                        }
                        break;
                    case PLAYER:
                        if (!suffix.isEmpty())
                        {
                            team = getScoreboardTeam("014S");
                        } else
                        {
                            team = getScoreboardTeam("014");
                        }
                        break;
                    default:
                        break;
                }

                if (team != null)
                {
                    ((CraftPlayer) players1).getHandle().playerConnection.sendPacket(team.addOrRemovePlayer(3, players2.getName()));
                }
            }
        }
    }

    public ScoreboardTeam getScoreboardTeam(String name)
    {
        return teams.stream().filter(team -> team.getName().equals(name)).findFirst().get();
    }

    public List<ScoreboardTeam> getTeams()
    {
        return teams;
    }

    @Override
    public void register()
    {
        String suffix = Constants.HOST_SUFFIX;

        teams.add(new ScoreboardTeam("001", Rank.ADMIN.getColor() + Rank.ADMIN.getPrefix(), ""));
        teams.add(new ScoreboardTeam("001S", Rank.ADMIN.getColor() + Rank.ADMIN.getPrefix(), " "+ suffix));
        teams.add(new ScoreboardTeam("002", Rank.RESP_DEV.getColor() + Rank.RESP_DEV.getPrefix(), ""));
        teams.add(new ScoreboardTeam("002S", Rank.RESP_DEV.getColor() + Rank.RESP_DEV.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("003", Rank.RESP_BUILD.getColor() + Rank.RESP_BUILD.getPrefix(), ""));
        teams.add(new ScoreboardTeam("003S", Rank.RESP_BUILD.getColor() + Rank.RESP_BUILD.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("004", Rank.RESP_MOD.getColor() + Rank.RESP_MOD.getPrefix(), ""));
        teams.add(new ScoreboardTeam("004S", Rank.RESP_MOD.getColor() + Rank.RESP_MOD.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("005", Rank.DEVELOPER.getColor() + Rank.DEVELOPER.getPrefix(), ""));
        teams.add(new ScoreboardTeam("005S", Rank.DEVELOPER.getColor() + Rank.DEVELOPER.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("006", Rank.STAFF.getColor() + Rank.STAFF.getPrefix(), ""));
        teams.add(new ScoreboardTeam("006S", Rank.STAFF.getColor() + Rank.STAFF.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("007", Rank.MODERATOR.getColor() + Rank.MODERATOR.getPrefix(), ""));
        teams.add(new ScoreboardTeam("007S", Rank.MODERATOR.getColor() + Rank.MODERATOR.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("008", Rank.BUILDER.getColor() + Rank.BUILDER.getPrefix(), ""));
        teams.add(new ScoreboardTeam("008S", Rank.BUILDER.getColor() + Rank.BUILDER.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("009", Rank.HELPER.getColor() + Rank.HELPER.getPrefix(), ""));
        teams.add(new ScoreboardTeam("009S", Rank.HELPER.getColor() + Rank.HELPER.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("010", Rank.PARTNER.getColor() + Rank.PARTNER.getPrefix(), ""));
        teams.add(new ScoreboardTeam("010S", Rank.PARTNER.getColor() + Rank.PARTNER.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("011", Rank.YOUTUBER.getColor() + Rank.YOUTUBER.getPrefix(), ""));
        teams.add(new ScoreboardTeam("011S", Rank.YOUTUBER.getColor() + Rank.YOUTUBER.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("012", Rank.LEGEND.getColor() + Rank.LEGEND.getPrefix(), ""));
        teams.add(new ScoreboardTeam("012S", Rank.LEGEND.getColor() + Rank.LEGEND.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("013", Rank.VIP.getColor() + Rank.VIP.getPrefix(), ""));
        teams.add(new ScoreboardTeam("013S", Rank.VIP.getColor() + Rank.VIP.getPrefix(), " " + suffix));
        teams.add(new ScoreboardTeam("014", Rank.PLAYER.getColor() + Rank.PLAYER.getPrefix(), ""));
        teams.add(new ScoreboardTeam("014S", Rank.PLAYER.getColor() + Rank.PLAYER.getPrefix(), " " + suffix));
    }

}
