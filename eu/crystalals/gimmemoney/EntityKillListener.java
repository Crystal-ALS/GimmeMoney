package eu.crystalals.gimmemoney;

import org.bukkit.entity.LivingEntity;
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
        	entity.getKiller().sendMessage("You killed an: " + entity.getName());
    }
}
