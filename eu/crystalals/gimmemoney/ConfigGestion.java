package eu.crystalals.gimmemoney;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigGestion
{
	private GimmeMoney plugin;
	private File file;
	private FileConfiguration customConfig;
	
	
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
