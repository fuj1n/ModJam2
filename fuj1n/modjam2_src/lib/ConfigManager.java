package fuj1n.modjam2_src.lib;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigManager {

	public static ConfigManager instance;
	
	private Configuration config;
	
	private Values values;
	
	public ConfigManager(File location){
		instance = this;
		config = new Configuration(location);
		values = new Values();
	}
	
	public Configuration getConfig(){
		return config;
	}
	
	public void readConfigValues(){
		config.load();
		values.secureCoreId = config.getBlock("Secure Core Id", values.secureCoreId).getInt();
		
		if(config.hasChanged()){
			config.save();
		}
	}
	
	public Values getValues(){
		return values;
	}
	
	public class Values{
		
		public int secureCoreId = 2444;
		
	}
	
}
