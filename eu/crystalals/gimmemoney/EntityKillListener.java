package eu.crystalals.gimmemoney;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityKillListener implements Listener
{
	public EntityKillListener(GimmeMoney plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
        LivingEntity entity = event.getEntity();
        
        if (entity.getKiller() != null)
        {
        	Player p = entity.getKiller();
        	String entity_name = entity.getName();
        	if (GimmeMoney.config.isEntityGivingMoney(entity_name))
        	{
        		double money_got = GimmeMoney.config.getMoney(p, entity_name);
        		GimmeMoney.economy.depositPlayer(p, money_got);
        		if (GimmeMoney.config.SendMessagesToPlayers())
        			p.sendMessage("§6Vous venez d'obtenir §c"
        				+ String.format("%." + GimmeMoney.config.howMuchRound() + "f", money_got)
        				+ GimmeMoney.config.Devise()
        				+ " §6pour avoir tuer §c" + entity_name + "§6 !");
        	}
        }
    }
}
