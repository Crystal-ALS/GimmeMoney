package eu.crystalals.gimmemoney;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ConfigGestion
{
	private GimmeMoney plugin;
	private File file;
	private FileConfiguration customConfig;
	private Random random = new Random();
	
	
	public ConfigGestion(GimmeMoney plugin)
	{
		// Create default config (config.yml)
		this.plugin = plugin;
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveDefaultConfig();
		
		// Create custom config (mobs.yml)
		File folder = Bukkit.getServer().getPluginManager().getPlugin("GimmeMoney").getDataFolder();
		file = new File(folder, "mobs.yml");
		
		// create the file
		if (!file.exists())
		{
			createNewFile();
		}
		
		customConfig = YamlConfiguration.loadConfiguration(file);
		setCustomDefault();
		customConfig.options().copyDefaults(true);
		save();
	}

	public boolean isEntityGivingMoney(String name)
	{
		return customConfig.contains(name);
	}
	
	/*
	 * This function get the money given by an entity,
	 * it set the return value to random from MinMoney and MaxMoney.
	 */
	public double getMoney(Player p, String name)
	{
		double min = customConfig.getDouble(name + ".MinMoney");
		double max = customConfig.getDouble(name + ".MaxMoney");
		double ret = min + (max - min) * random.nextDouble();
		double ret_rounded = round(ret, plugin.getConfig().getInt("AfterDotNumbers"));
		return permissionMultiplier(p, ret_rounded);
	}
	
	public void SetMoney(String name, double min_value, double max_value)
	{
		customConfig.set(name + ".MinMoney" , min_value);
		customConfig.set(name + ".MaxMoney", max_value);
		save();
	}
	
	public boolean SendMessagesToPlayers()
	{
		return plugin.getConfig().getBoolean("EnableChatMessage");
	}
	
	public String Devise()
	{
		return plugin.getConfig().getString("MoneyUsed");
	}
	
	public int howMuchRound()
	{
		System.out.println(plugin.getConfig().getInt("AfterDotNumbers"));
		return plugin.getConfig().getInt("AfterDotNumbers");
	}
	
	private double permissionMultiplier(Player p, double ret)
	{
		if (plugin.getConfig().getBoolean("MoneyBoost.Enabled"))
		{
			Set<String> keys = plugin.getConfig().getConfigurationSection("MoneyBoost.Boost").getKeys(false);
			for (String key : keys)
			{
				if (p.hasPermission("gimmemoney.boost." + key))
				{
					return plugin.getConfig().getDouble("MoneyBoost.Boost." + key) * ret;
				}
			}
			return ret;
		}
		else
		{
			return ret;
		}
	}
	
	private void setCustomDefault()
	{
		// Cow configs
		customConfig.addDefault("Cow.MinMoney", 10.00);
		customConfig.addDefault("Cow.MaxMoney", 40.00);
	}
	
	/*
	 * Allow us to round the given number at n decimals
	 */
	private double round(double to_round, int n)
	{
		if (n == 0)
			return Math.round(to_round);
		double power_ten = Math.pow(10, n);
		return ((double)Math.round(to_round * power_ten)) / power_ten;
	}
	
	private void createNewFile()
	{
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			System.out.println("Could not create mobs.yml");
		}
	}
	
	private void save()
	{
		try
		{
			customConfig.save(file);
		}
		catch (IOException e)
		{
			System.out.println("Could not save mobs.yml");
		}
	}
}
