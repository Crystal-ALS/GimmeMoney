package eu.crystalals.gimmemoney;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RightClickListener implements Listener
{
	public RightClickListener(GimmeMoney plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent event)
	{
		Player p = event.getPlayer();
		if (CommandSetMoney.players.containsKey(p.getName()))
		{
			double min_money = CommandSetMoney.players.get(p.getName())[0];
			double max_money = CommandSetMoney.players.get(p.getName())[1];
			CommandSetMoney.players.remove(p.getName());
			
			GimmeMoney.config.SetMoney(event.getRightClicked().getName(), min_money, max_money);
			
			p.sendMessage("§6You just set the money for killing §c"
					+ event.getRightClicked().getName() + "§6 to:");
			p.sendMessage("§6min: §c" + min_money + " §6max: §c" + max_money);
		}
	}
	
	/*
	 * Remove player from players if he disconnect
	 */
	@EventHandler
	public void onDisconned(PlayerQuitEvent event)
	{
		String name = event.getPlayer().getName();
		if (CommandSetMoney.players.containsKey(name))
			CommandSetMoney.players.remove(name);
	}
}
