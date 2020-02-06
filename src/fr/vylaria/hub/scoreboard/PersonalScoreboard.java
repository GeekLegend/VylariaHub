package fr.vylaria.hub.scoreboard;

import fr.vylaria.api.VylariaAPI;
import fr.vylaria.hub.VylariaHub;
import fr.vylaria.api.player.account.Account;
import fr.vylaria.api.player.account.Rank;
import fr.vylaria.api.player.account.MongoAccount;
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
		MongoAccount mongoAccount = VylariaHub.getInstance().getMongoAccount();
		Account account = mongoAccount.get(player.getUniqueId());
		Rank rank = account.getRank();
		double tokens = account.getTokens();
		double eurs = account.getEurs();
		String hub = VylariaAPI.getInstance().getVServer().getServerName();
		int online = VylariaAPI.getInstance().getNetwork().getPlayerCount();

		objectiveSign.setDisplayName("§e§lVYLARIA");
		objectiveSign.setLine(0, "§1§6 ");
		objectiveSign.setLine(1, "§6§lInfos");
		objectiveSign.setLine(2, " §7Compte: §6" + player.getName());
		objectiveSign.setLine(3, " §7Grade: " + rank.getColor() + rank.getPrefix().replace("[", "").replace("]", ""));
		objectiveSign.setLine(4, "§1§2 ");
		objectiveSign.setLine(5, "§6§lMonnaies");
		objectiveSign.setLine(6, " §7Jetons: §a" + tokens + " ⛃");
		objectiveSign.setLine(7, " §7EUR: §d" + eurs);
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