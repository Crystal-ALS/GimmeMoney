package eu.crystalals.gimmemoney;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
		customConfig.addDefault("cow.MinMoney", 10.00);
		customConfig.addDefault("cow.MaxMoney", 40.00);
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
	public double getMoney(String name)
	{
		double min = customConfig.getDouble(name + ".MinMoney");
		double max = customConfig.getDouble(name + ".MaxMoney");
		double ret = min + (max - min) * random.nextDouble();
		return round(ret, plugin.getConfig().getInt("AfterDotNumber"));
	}
	
	/*
	 * Allow us to round the given number at n decimals
	 */
	private double round(double to_round, int n)
	{
		double power_ten = Math.pow(to_round, n);
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
