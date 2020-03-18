package eu.crystalals.gimmemoney;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class GimmeMoney extends JavaPlugin implements Listener {
	
	static Economy economy = null;
	
    @Override
    public void onEnable() {
    	
    	Bukkit.getPluginManager().registerEvents(this, this);
    	
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
    public void onDisable() {
    	
    	

    }

}
