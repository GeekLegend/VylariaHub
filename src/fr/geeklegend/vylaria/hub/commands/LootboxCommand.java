package fr.geeklegend.vylaria.hub.commands;

import fr.geeklegend.vylaria.hub.VylariaHub;
import fr.geeklegend.vylaria.hub.lootbox.Lootbox;
import fr.vylaria.api.account.Account;
import fr.vylaria.api.account.Rank;
import fr.vylaria.api.account.RedisAccount;
import fr.vylaria.api.builders.ItemBuilder;
import fr.vylaria.api.utils.Constants;
import fr.vylaria.api.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LootboxCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        RedisAccount redisAccount = VylariaHub.getInstance().getRedisAccount();
        Account account = redisAccount.get(player.getUniqueId());
        Rank rank = account.getRank();

        if (rank.getPower() >= Rank.ADMIN.getPower())
        {
            if (args.length == 0)
            {
                player.sendMessage("§e===== §e§lLootbox - Aide §e=====");
                player.sendMessage("§e/lootbox give <player> §7- Donner une clé à un joueur.");

                return false;
            } else
            {
                if (args[0].equalsIgnoreCase("give"))
                {
                    if (args.length < 3)
                    {
                        player.sendMessage(Lootbox.PREFIX + "§c/lootbox give <player> <amount>");
                    } else
                    {
                        Player target = VylariaHub.getInstance().getServer().getPlayer(args[1]);

                        if (!VylariaHub.getInstance().getServer().getOnlinePlayers().contains(target))
                        {
                            player.sendMessage(Lootbox.PREFIX + "§cLe joueur " + StringUtils.capitalize(args[1]) + " n'est pas en ligne.");

                            return false;
                        } else
                        {
                            if (Utils.isInt(args[2]))
                            {
                                int amount = Integer.valueOf(args[2]);

                                if (amount != 0)
                                {
                                    target.getInventory().addItem(new ItemBuilder(Material.TRIPWIRE_HOOK, amount).setGlowing().setName(Lootbox.PREFIX + "§eClé").toItemStack());

                                    target.sendMessage(Lootbox.PREFIX + "§7Vous avez reçu une clé.");
                                    player.sendMessage(Lootbox.PREFIX + "§7Vous avez donner une clé au joueur §e" + StringUtils.capitalize(args[1]) + "§7.");
                                }
                            }
                        }
                    }
                }
            }
        } else
        {
            player.sendMessage(Constants.NO_PERMISSION);
        }

        return false;
    }
}
