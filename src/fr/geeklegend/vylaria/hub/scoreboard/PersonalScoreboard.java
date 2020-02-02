package fr.geeklegend.vylaria.hub.scoreboard;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.servers.RedisServer;
import fr.vylaria.api.servers.Server;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PersonalScoreboard
{
	private Player player;
	private final UUID uuid;
	private final ObjectiveSign objectiveSign;
	private Scoreboard scoreboard;

	public PersonalScoreboard(Player player)
	{
		this.setPlayer(player);
		uuid = player.getUniqueId();
		objectiveSign = new ObjectiveSign("sidebar", "DevPlugin");

		reloadData();
		objectiveSign.addReceiver(player);

		this.scoreboard = VylariaHub.getInstance().getScoreboard();

		scoreboard.createTablist(player);
	}

	public void reloadData()
	{
	}

	public void setLines(String ip)
	{
		RedisAccount redisAccount = VylariaHub.getInstance().getRedisAccount();
		RedisServer redisServer = VylariaHub.getInstance().getRedisServer();
		Server networkServer = redisServer.get("Network");
		Account account = redisAccount.get(player.getUniqueId());
		Rank rank = account.getRank();
		float coins = account.getCoins();
		float eur = account.getEur();
		String hub = VylariaHub.getInstance().getServer().getMotd();
		int online = networkServer.getOnline();

		objectiveSign.setDisplayName("§e§lVYLARIA");
		objectiveSign.setLine(0, "§1§6 ");
		objectiveSign.setLine(1, "§6§lInfos");
		objectiveSign.setLine(2, " §7Compte: §6" + player.getName());
		objectiveSign.setLine(3, " §7Grade: " + rank.getColor() + rank.getPrefix().replace("[", "").replace("]", ""));
		objectiveSign.setLine(4, "§1§2 ");
		objectiveSign.setLine(5, "§6§lMonnaies");
		objectiveSign.setLine(6, " §7Jetons: §a" + coins + " ⛃");
		objectiveSign.setLine(7, " §7EUR: §d" + eur);
		objectiveSign.setLine(8, "§1§1 ");
		objectiveSign.setLine(9, "§6§lServeur");
		objectiveSign.setLine(10, " §7Hub: §c" + hub);
		objectiveSign.setLine(11, " §7Joueurs: §e" + online);
		objectiveSign.setLine(12, "§1§4 ");
		objectiveSign.setLine(13, ip);
		objectiveSign.updateLines();
	}

	public void onLogout()
	{
		objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}
}