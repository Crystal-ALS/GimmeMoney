package eu.crystalals.gimmemoney;

import java.util.TreeMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetMoney implements CommandExecutor
{
	static public TreeMap<String, double[]> players = new TreeMap<String, double[]>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String written, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player)sender;
			if (p.hasPermission("gimmemoney.admin"))
			{
				if (args.length != 2)
				{
					sendUsage(p);
					return false;
				}
				double min_money;
				double max_money;
				try
				{
					min_money = Double.parseDouble(args[0]);
					max_money = Double.parseDouble(args[1]);
				}
				catch (Exception e)
				{
					sendUsage(p);
					return false;
				}
				players.put(p.getName(), new double[] {min_money, max_money});
				
				p.sendMessage("§6Right click on set entity you want to set the money.");
				return true;
			}
			else
			{
				p.sendMessage("§4You are not allowed to do that!");
				return false;
			}
		}
		else
		{
			sender.sendMessage("Only players can execute this command");
			return false;
		}
	}
	
	private void sendUsage(Player p)
	{
		p.sendMessage("§6Usage : /setmoney <MinAmount> <MaxAmount>");
	}
}
