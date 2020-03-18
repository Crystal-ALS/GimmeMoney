package eu.crystalals.gimmemoney;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class GimmeMoney extends JavaPlugin
{
	
	static public Economy economy = null;
	static public ConfigGestion config = null;
	
    @Override
    public void onEnable()
    {
    	// register events
    	new EntityKillListener(this);
    	new RightClickListener(this);
    	
    	// Load configs
    	if (config == null)
    		config = new ConfigGestion(this);
    	
    	// set economy
    	if (!setupEconomy())
    		System.out.println("Unable to find an economy plugin (IE essentials)!");
    	
    	// register command
    	getCommand("setmoney").setExecutor(new CommandSetMoney());
    }
    
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
        {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
        {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    @Override
    public void onDisable()
    {
    }

}
