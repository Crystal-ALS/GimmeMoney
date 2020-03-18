package eu.crystalals.gimmemoney;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class GimmeMoney extends JavaPlugin
{
	
	static Economy economy = null;
	
    @Override
    public void onEnable()
    {
    	// register events
    	new EntityKillListener(this);
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
