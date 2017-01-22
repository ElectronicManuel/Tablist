package me.reverser.tablist.controller;

import org.bukkit.command.CommandSender;

public class MessageController {
	
	private static String colorPrimary = "§9";
	private static String colorSecondary = "§b";
	private static String colorFiller = "§7";
	private static String colorSuccess = "§a";
	private static String colorWarn = "§c";
	private static String colorFatal = "§4";
	private static String prefix = colorFiller + "[" + colorPrimary + "Tab" + colorSecondary + "list" + colorFiller + "]§f"; 
	
	public static void help(CommandSender toHelp) {
		toHelp.sendMessage("");
		toHelp.sendMessage(getPrefix() + " Hilfe:");
		toHelp.sendMessage(colorFiller + "- " + colorPrimary + "tablist reload" + colorFiller + " - " + colorSecondary + "Aktualisiert die Tablist");
		toHelp.sendMessage("");
	}
	
	public static void sendPermissionDenial(CommandSender denied) {
		denied.sendMessage(getPrefix() + getColorWarn() + " Dazu hast du keine Rechte");
	}
	
	public static String format(String toFormat) {
		toFormat = toFormat.replaceAll("&", "§");
		toFormat = toFormat.replaceAll("%PREFIX%", getPrefix());
		
		return toFormat;
	}

	public static String getPrefix() {
		return prefix;
	}

	public static void setPrefix(String prefix) {
		MessageController.prefix = prefix;
	}

	public static String getColorPrimary() {
		return colorPrimary;
	}

	public static void setColorPrimary(String colorPrimary) {
		MessageController.colorPrimary = colorPrimary;
	}

	public static String getColorSecondary() {
		return colorSecondary;
	}

	public static void setColorSecondary(String colorSecondary) {
		MessageController.colorSecondary = colorSecondary;
	}

	public static String getColorFiller() {
		return colorFiller;
	}

	public static void setColorFiller(String colorFiller) {
		MessageController.colorFiller = colorFiller;
	}

	public static String getColorSuccess() {
		return colorSuccess;
	}

	public static void setColorSuccess(String colorSuccess) {
		MessageController.colorSuccess = colorSuccess;
	}

	public static String getColorWarn() {
		return colorWarn;
	}

	public static void setColorWarn(String colorWarn) {
		MessageController.colorWarn = colorWarn;
	}

	public static String getColorFatal() {
		return colorFatal;
	}

	public static void setColorFatal(String colorFatal) {
		MessageController.colorFatal = colorFatal;
	}

}
