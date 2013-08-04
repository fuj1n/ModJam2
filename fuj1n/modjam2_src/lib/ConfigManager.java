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
		values.secureBlockId = config.getBlock("Secure Block Id", values.secureBlockId).getInt();
		values.secureDoorBlockId = config.getBlock("Secure Door Block Id", values.secureDoorBlockId).getInt();
		
		values.securityPassId = config.getItem("Security Pass Id", values.securityPassId).getInt();
		values.securityWrenchId = config.getItem("Security Wrench Id", values.securityWrenchId).getInt();
		values.secureDoorItemId = config.getItem("Secure Door Item Id", values.secureDoorItemId).getInt();
		
		if(config.hasChanged()){
			config.save();
		}
	}
	
	public Values getValues(){
		return values;
	}
	
	public class Values{
		
		public int secureCoreId = 2444;
		public int secureBlockId = 2445;
		public int secureDoorBlockId = 2446;
		
		public int securityPassId = 16000;
		public int securityWrenchId = 16001;
		public int secureDoorItemId = 16002;
	}
	
}
