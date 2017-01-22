package me.reverser.tablist.controller;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.reverser.tablist.main.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class TablistController {
	
	// Instanz Variabel da diese Klasse nur einmal instanziiert wird
	private static TablistController instance;
	public static TablistController getInstance() {
		return instance;
	}
	
	private String defaultHeader = "";
	private String defaultFooter = "";
	
	public TablistController() {
		instance = this;
	}
	
	private void load() {
		Main.getInstance().reloadConfig();
		defaultHeader = Main.getInstance().getConfig().getString("header");
		defaultFooter = Main.getInstance().getConfig().getString("footer");
	}
	
	public void reload() {
		load();
		for(Player p : Bukkit.getOnlinePlayers()) {
			setDefaultHeader(p);
		}
	}
	
	public void setDefaultHeader(Player p) {
		setHeader(p, defaultHeader, defaultFooter);
	}
	
	public void setHeader(Player p, String header, String footer) {
		header = MessageController.format(header);
		footer = MessageController.format(footer);
		try {
			CraftPlayer cplayer = (CraftPlayer) p;
			PlayerConnection connection = cplayer.getHandle().playerConnection;
			connection.sendPacket(getPacket(header, footer));
			System.out.println("Sende Spieler '" + p.getName() + "' Header '" + header + "' und Footer '" + footer + "'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private PacketPlayOutPlayerListHeaderFooter getPacket(String headerText, String footerText) {
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		IChatBaseComponent header = ChatSerializer.a("{'text':'" + headerText + "'}");
		IChatBaseComponent footer = ChatSerializer.a("{'text':'" + footerText + "'}");
		try {
			// Setze den Wert des Headers
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, header);
			headerField.setAccessible(false);
			
			// Setze den Wert des Footers
			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, footer);
			footerField.setAccessible(false);
		}
		catch(Exception ex) {}
		
		return packet;
	}

}
