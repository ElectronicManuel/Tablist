package me.reverser.tablist.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.reverser.tablist.controller.TablistController;
import me.reverser.tablist.event.PlayerListener;
import me.reverser.tablist.executor.TablistExecutor;

public class Main extends JavaPlugin {
	
	// Statische instance variabel für Singleton Zugriff
	private static Main instance = null;
	public static Main getInstance() {
		return instance;
	}
	
	private TablistExecutor tablistExe = null;
	private TablistController tabController = null;
	private PlayerListener playerListener = null;
	
	@Override
	public void onEnable() {
		init();
		System.out.println(getDescription().getName() + " v" + getDescription().getVersion() + " is enabled");
	}
	
	@Override
	public void onDisable() {
		System.out.println(getDescription().getName() + " v" + getDescription().getVersion() + " is disabled");
	}
	
	private void init() {
		instance = this;
		saveDefaultConfig();
		tabController = new TablistController();
		
		initCommands();
		initEvents();
		
		tabController.reload();
	}
	
	private void initCommands() {
		tablistExe = new TablistExecutor();
		getCommand("tablist").setExecutor(tablistExe);
	}
	
	private void initEvents() {
		playerListener = new PlayerListener();
		Bukkit.getPluginManager().registerEvents(playerListener, this);
	}
	
}
