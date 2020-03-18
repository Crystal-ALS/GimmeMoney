package eu.crystalals.gimmemoney;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class GimmeMoney extends JavaPlugin implements Listener {
	
	static Economy economy = null;
	
    @Override
    public void onEnable()
    {
    	getServer().getPluginManager().registerEvents(this, this);
    	//Bukkit.getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
        LivingEntity entity = event.getEntity();
        
        if (entity.getKiller() != null)
        	entity.getKiller().sendMessage("You killed an: " + entity.getName());
    }
    
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    @Override
    public void onDisable()
    {
    	
    	

    }

}
