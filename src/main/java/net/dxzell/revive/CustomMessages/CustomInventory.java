/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 */
package com.dxzell.revive.CustomMessages;

import com.dxzell.revive.Config;
import com.dxzell.revive.Revive;
import java.util.function.Function;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CustomInventory {
    private Revive main;
    private Inventory inv = Bukkit.createInventory(null, (int)9, (String)(ChatColor.WHITE + "Customize messages " + ChatColor.BLACK + ">>"));
    private Function<String, String> cutString;

    public CustomInventory(Revive main) {
        this.main = main;
        this.cutString = text -> {
            ChatColor color = ChatColor.WHITE;
            StringBuilder builder = new StringBuilder();
            int counter = 0;
            for (String string : text.split(" ")) {
                counter += string.length();
                if (!string.contains("&")) {
                    if (counter > 20) {
                        builder.append(color + string + "\u00b0");
                        counter = 0;
                    } else {
                        builder.append(color + string + " ");
                    }
                } else if (counter > 20) {
                    builder.append(string + "\u00b0");
                    counter = 0;
                } else {
                    builder.append(string + " ");
                }
                for (int i = 0; i < string.length(); ++i) {
                    if (string.charAt(i) != '&' || i + 1 > string.length()) continue;
                    color = ChatColor.getByChar((char)string.charAt(i + 1));
                }
            }
            return builder.toString();
        };
        this.inv.setItem(0, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Player knocked", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears in the\u00b0" + ChatColor.GRAY + "chat when a player\u00b0" + ChatColor.GRAY + "has been knocked.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(Config.getPlayerKnockedMessage("[player]"))) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(8, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Player not nearby", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears in the\u00b0" + ChatColor.GRAY + "bossbar if no player\u00b0" + ChatColor.GRAY + "is nearby.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(Config.getPressSneak())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(7, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Player nearby", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears in the\u00b0" + ChatColor.GRAY + "bossbar if a player\u00b0" + ChatColor.GRAY + "is nearby.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(Config.getCantPressSneak())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(2, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Message above player", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears above the player\u00b0" + ChatColor.GRAY + "after he gets knocked down.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + (main.getConfigClass().getArmorStand(0).contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)main.getConfigClass().getArmorStand(0)) : ChatColor.WHITE + main.getConfigClass().getArmorStand(0)) + "\u00b0" + (main.getConfigClass().getArmorStand(1).contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)main.getConfigClass().getArmorStand(1)) : ChatColor.WHITE + main.getConfigClass().getArmorStand(1)) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(3, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Title and Subtitle", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "the player sees after dying.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + (main.getConfigClass().getTitle(true).contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)main.getConfigClass().getTitle(true)) : ChatColor.WHITE + main.getConfigClass().getTitle(true)) + "\u00b0" + (main.getConfigClass().getTitle(false).contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)main.getConfigClass().getTitle(false)) : ChatColor.WHITE + main.getConfigClass().getTitle(false)) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(4, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Actionbar", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "the knocked player sees\u00b0" + ChatColor.GRAY + "in the actionbar. The Timer\u00b0" + ChatColor.GRAY + "will be at the end.\u00b0" + ChatColor.GRAY + "of the message\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + (main.getConfigClass().getActionbar().contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)main.getConfigClass().getActionbar()) : ChatColor.WHITE + main.getConfigClass().getActionbar()) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(5, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Wrong command usage", ChatColor.GRAY + "This message appears in the chat\u00b0" + ChatColor.GRAY + "when players use the Revive\u00b0" + ChatColor.GRAY + "command incorrectly.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(main.getConfigClass().getWrongUsage())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(6, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "New revive item message", ChatColor.GRAY + "This message appears in the chat\u00b0" + ChatColor.GRAY + "when the player sets a\u00b0" + ChatColor.GRAY + "new revive item in the settings.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(main.getConfigClass().getNewReviveItemMessage())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(1, main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Player bled out", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears in the\u00b0" + ChatColor.GRAY + "chat after a player\u00b0" + ChatColor.GRAY + "bled out.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(Config.getPlayerDiedMessage())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
    }

    public void update() {
        this.inv.setItem(0, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Player knocked", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears in the\u00b0" + ChatColor.GRAY + "chat when a player\u00b0" + ChatColor.GRAY + "has been knocked.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(Config.getPlayerKnockedMessage("[player]"))) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(8, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Player not nearby", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears in the\u00b0" + ChatColor.GRAY + "bossbar if no player\u00b0" + ChatColor.GRAY + "is nearby.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(Config.getPressSneak())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(7, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Player nearby", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears in the\u00b0" + ChatColor.GRAY + "bossbar if a player\u00b0" + ChatColor.GRAY + "is nearby.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(Config.getCantPressSneak())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(2, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Message above player", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears above the player\u00b0" + ChatColor.GRAY + "after he gets knocked down.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + (this.main.getConfigClass().getArmorStand(0).contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getArmorStand(0)) : ChatColor.WHITE + this.main.getConfigClass().getArmorStand(0)) + "\u00b0" + (this.main.getConfigClass().getArmorStand(1).contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getArmorStand(1)) : ChatColor.WHITE + this.main.getConfigClass().getArmorStand(1)) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(3, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Title and Subtitle", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "the player sees after dying.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + (this.main.getConfigClass().getTitle(true).contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getTitle(true)) : ChatColor.WHITE + this.main.getConfigClass().getTitle(true)) + "\u00b0" + (this.main.getConfigClass().getTitle(false).contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getTitle(false)) : ChatColor.WHITE + this.main.getConfigClass().getTitle(false)) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(4, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Actionbar", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "the knocked player sees\u00b0" + ChatColor.GRAY + "in the actionbar. The Timer\u00b0" + ChatColor.GRAY + "will be at the end.\u00b0" + ChatColor.GRAY + "of the message\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + (this.main.getConfigClass().getActionbar().contains("&") ? ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getActionbar()) : ChatColor.WHITE + this.main.getConfigClass().getActionbar()) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(5, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Wrong command usage", ChatColor.GRAY + "This message appears in the chat\u00b0" + ChatColor.GRAY + "when players use the Revive\u00b0" + ChatColor.GRAY + "command incorrectly.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(this.main.getConfigClass().getWrongUsage())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(6, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "New revive item message", ChatColor.GRAY + "This message appears in the chat\u00b0" + ChatColor.GRAY + "when the player sets a\u00b0" + ChatColor.GRAY + "new revive item in the settings.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(this.main.getConfigClass().getNewReviveItemMessage())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.inv.setItem(1, this.main.buildItemStack(Material.PAPER, ChatColor.GOLD + "Player bled out", ChatColor.GRAY + "This is the message\u00b0" + ChatColor.GRAY + "that appears in the\u00b0" + ChatColor.GRAY + "chat after a player\u00b0" + ChatColor.GRAY + "bled out.\u00b0\u00b0" + ChatColor.GRAY + "Current:\u00b0" + ChatColor.translateAlternateColorCodes((char)'&', (String)this.cutString.apply(Config.getPlayerDiedMessage())) + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
    }

    public void openInv(Player player) {
        player.openInventory(this.inv);
    }

    public Inventory getInv() {
        return this.inv;
    }
}

