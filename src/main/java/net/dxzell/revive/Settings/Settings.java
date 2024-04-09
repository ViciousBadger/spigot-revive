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
package com.dxzell.revive.Settings;

import com.dxzell.revive.Config;
import com.dxzell.revive.Revive;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Settings {
    private Revive main;
    private Inventory settingsInv;
    private Inventory timeInv;
    private Inventory detectionRangeInv;

    public Settings(Revive main) {
        int i;
        this.main = main;
        this.settingsInv = Bukkit.createInventory(null, (int)45, (String)(ChatColor.WHITE + "Settings " + ChatColor.BLACK + ">>"));
        for (i = 0; i < 45; ++i) {
            this.settingsInv.setItem(i, main.buildItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", " ", false));
        }
        this.settingsInv.setItem(12, main.buildItemStack(Material.CLOCK, ChatColor.AQUA + "Time", ChatColor.GRAY + "The player can be revived\u00b0" + ChatColor.GRAY + "in that time.\u00b0\u00b0" + ChatColor.GRAY + "Current time: " + ChatColor.GOLD + main.getConfigClass().getTime() / 60 + ChatColor.GOLD + ":" + (main.getConfigClass().getTime() % 60 >= 10 ? "" + ChatColor.GOLD + main.getConfigClass().getTime() % 60 : ChatColor.GOLD + "0" + ChatColor.GOLD + main.getConfigClass().getTime() % 60) + (main.getConfigClass().getTime() / 60 > 1 ? ChatColor.GOLD + " minutes" : ChatColor.GOLD + " minute") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(13, main.buildItemStack(main.getConfigClass().getMobDamage() ? Material.REDSTONE_BLOCK : Material.EMERALD_BLOCK, ChatColor.RED + "Mob damage", main.getConfigClass().getMobDamage() ? ChatColor.GRAY + "Mobs " + ChatColor.RED + "can " + ChatColor.GRAY + "finish/kill\u00b0" + ChatColor.GRAY + "knocked players\u00b0\u00b0" + ChatColor.YELLOW + "[Click to toggle]" : ChatColor.GRAY + "Mobs " + ChatColor.GREEN + "cannot " + ChatColor.GRAY + "finish/kill\u00b0" + ChatColor.GRAY + "knocked players\u00b0\u00b0" + ChatColor.YELLOW + "[Click to toggle]", true));
        this.settingsInv.setItem(14, main.buildItemStack(Material.getMaterial((String)main.getConfigClass().getType()), ChatColor.DARK_GRAY + "Item_Type", ChatColor.GRAY + "The player can only get revived\u00b0" + ChatColor.GRAY + "with the following item: \u00b0" + ChatColor.GOLD + main.getConfigClass().getType() + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(21, main.buildItemStack(Material.COMPASS, ChatColor.GOLD + "Detection range", ChatColor.GRAY + "This is the range in which a player\u00b0" + ChatColor.GRAY + "is being recognized as " + ChatColor.DARK_GRAY + "'nearby'\u00b0\u00b0" + ChatColor.GRAY + "Currently at: " + ChatColor.GOLD + main.getConfigClass().getDetectionRange() + ChatColor.GRAY + " blocks from\u00b0" + ChatColor.GRAY + "the knocked players location\u00b0" + ChatColor.GRAY + "into every direction.\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(22, main.buildItemStack(main.getConfigClass().getAnimation() ? Material.GREEN_TERRACOTTA : Material.RED_TERRACOTTA, main.getConfigClass().getAnimation() ? ChatColor.GREEN + "Animation" : ChatColor.RED + "Animation", ChatColor.GRAY + "Show death and revival animation: \u00b0" + (main.getConfigClass().getAnimation() ? ChatColor.GREEN + "true" : ChatColor.RED + "false") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(23, main.buildItemStack(Config.getTombstone() ? Material.GREEN_TERRACOTTA : Material.RED_TERRACOTTA, Config.getTombstone() ? ChatColor.GREEN + "Tombstone" : ChatColor.RED + "Tombstone", ChatColor.GRAY + "Spawn tombstone after death: \u00b0" + (Config.getTombstone() ? ChatColor.GREEN + "true" : ChatColor.RED + "false") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(31, main.buildItemStack(Material.TOTEM_OF_UNDYING, Config.getTotem() ? ChatColor.GREEN + "Totem" : ChatColor.RED + "Totem", ChatColor.GRAY + "Still get knocked with\u00b0" + ChatColor.GRAY + "a totem in your hand:\u00b0" + (Config.getTotem() ? ChatColor.GREEN + "true" : ChatColor.RED + "false") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.timeInv = Bukkit.createInventory(null, (int)9, (String)(ChatColor.WHITE + "Length " + ChatColor.BLACK + ">>"));
        for (i = 0; i < 9; ++i) {
            this.timeInv.setItem(i, main.buildItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", " ", false));
        }
        this.timeInv.setItem(1, main.buildItemStack(Material.REDSTONE_TORCH, ChatColor.RED + "-", ChatColor.GRAY + "Lower the time by\u00b0" + ChatColor.DARK_GRAY + "30 " + ChatColor.GRAY + "seconds\u00b0\u00b0" + ChatColor.GRAY + "Current time: " + ChatColor.GOLD + main.getConfigClass().getTime() / 60 + ChatColor.GOLD + ":" + (main.getConfigClass().getTime() % 60 >= 10 ? "" + ChatColor.GOLD + main.getConfigClass().getTime() % 60 : ChatColor.GOLD + "0" + ChatColor.GOLD + main.getConfigClass().getTime() % 60) + (main.getConfigClass().getTime() / 60 > 1 ? ChatColor.GOLD + " minutes" : ChatColor.GOLD + " minute") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to lower]", true));
        this.timeInv.setItem(4, main.buildItemStack(Material.EMERALD_BLOCK, ChatColor.GOLD + "Return to settings", "\u00b0" + ChatColor.YELLOW + "[Click to return]", true));
        this.timeInv.setItem(7, main.buildItemStack(Material.TORCH, ChatColor.GREEN + "+", ChatColor.GRAY + "Higher the time by\u00b0" + ChatColor.DARK_GRAY + "30 " + ChatColor.GRAY + "seconds\u00b0\u00b0" + ChatColor.GRAY + "Current time: " + ChatColor.GOLD + main.getConfigClass().getTime() / 60 + ChatColor.GOLD + ":" + (main.getConfigClass().getTime() % 60 >= 10 ? "" + ChatColor.GOLD + main.getConfigClass().getTime() % 60 : ChatColor.GOLD + "0" + ChatColor.GOLD + main.getConfigClass().getTime() % 60) + (main.getConfigClass().getTime() / 60 > 1 ? ChatColor.GOLD + " minutes" : ChatColor.GOLD + " minute") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to higher]", true));
        this.detectionRangeInv = Bukkit.createInventory(null, (int)9, (String)(ChatColor.WHITE + "Detection range " + ChatColor.BLACK + ">>"));
        for (i = 0; i < 9; ++i) {
            this.detectionRangeInv.setItem(i, main.buildItemStack(Material.GRAY_STAINED_GLASS_PANE, " ", " ", false));
        }
        this.detectionRangeInv.setItem(1, main.buildItemStack(Material.REDSTONE_TORCH, ChatColor.RED + "-", ChatColor.GRAY + "Lower the detection range by\u00b0" + ChatColor.DARK_GRAY + "5 " + ChatColor.GRAY + "blocks\u00b0\u00b0" + ChatColor.GRAY + "Current detection range: " + ChatColor.GOLD + main.getConfigClass().getDetectionRange() + ChatColor.GRAY + " blocks\u00b0\u00b0" + ChatColor.YELLOW + "[Click to lower]", true));
        this.detectionRangeInv.setItem(4, main.buildItemStack(Material.EMERALD_BLOCK, ChatColor.GOLD + "Return to settings", "\u00b0" + ChatColor.YELLOW + "[Click to return]", true));
        this.detectionRangeInv.setItem(7, main.buildItemStack(Material.TORCH, ChatColor.GREEN + "+", ChatColor.GRAY + "Higher the detection range\u00b0" + ChatColor.DARK_GRAY + "5 " + ChatColor.GRAY + "blocks\u00b0\u00b0" + ChatColor.GRAY + "Current detection range: " + ChatColor.GOLD + main.getConfigClass().getDetectionRange() + ChatColor.GRAY + " blocks\u00b0\u00b0" + ChatColor.YELLOW + "[Click to higher]", true));
    }

    public void updateSettingsInv() {
        this.settingsInv.setItem(12, this.main.buildItemStack(Material.CLOCK, ChatColor.AQUA + "Time", ChatColor.GRAY + "The player can be revived\u00b0" + ChatColor.GRAY + "in that time.\u00b0\u00b0" + ChatColor.GRAY + "Current time: " + ChatColor.GOLD + this.main.getConfigClass().getTime() / 60 + ChatColor.GOLD + ":" + (this.main.getConfigClass().getTime() % 60 >= 10 ? "" + ChatColor.GOLD + this.main.getConfigClass().getTime() % 60 : ChatColor.GOLD + "0" + ChatColor.GOLD + this.main.getConfigClass().getTime() % 60) + (this.main.getConfigClass().getTime() / 60 > 1 ? ChatColor.GOLD + " minutes" : ChatColor.GOLD + " minute") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(13, this.main.buildItemStack(this.main.getConfigClass().getMobDamage() ? Material.REDSTONE_BLOCK : Material.EMERALD_BLOCK, ChatColor.RED + "Mob damage", this.main.getConfigClass().getMobDamage() ? ChatColor.GRAY + "Mobs " + ChatColor.RED + "can " + ChatColor.GRAY + "finish/kill\u00b0" + ChatColor.GRAY + "knocked players\u00b0\u00b0" + ChatColor.YELLOW + "[Click to toggle]" : ChatColor.GRAY + "Mobs " + ChatColor.GREEN + "cannot " + ChatColor.GRAY + "finish/kill\u00b0" + ChatColor.GRAY + "knocked players\u00b0\u00b0" + ChatColor.YELLOW + "[Click to toggle]", true));
        this.settingsInv.setItem(14, this.main.buildItemStack(Material.getMaterial((String)this.main.getConfigClass().getType()), ChatColor.DARK_GRAY + "Item_Type", ChatColor.GRAY + "The player can only get revived\u00b0" + ChatColor.GRAY + "with the following item: \u00b0" + ChatColor.GOLD + this.main.getConfigClass().getType() + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(21, this.main.buildItemStack(Material.COMPASS, ChatColor.GOLD + "Detection range", ChatColor.GRAY + "This is the range in which a player\u00b0" + ChatColor.GRAY + "is being recognized as " + ChatColor.DARK_GRAY + "'nearby'\u00b0\u00b0" + ChatColor.GRAY + "Currently at: " + ChatColor.GOLD + this.main.getConfigClass().getDetectionRange() + ChatColor.GRAY + " blocks from\u00b0" + ChatColor.GRAY + "the knocked players location\u00b0" + ChatColor.GRAY + "into every direction.\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(22, this.main.buildItemStack(this.main.getConfigClass().getAnimation() ? Material.GREEN_TERRACOTTA : Material.RED_TERRACOTTA, this.main.getConfigClass().getAnimation() ? ChatColor.GREEN + "Animation" : ChatColor.RED + "Animation", ChatColor.GRAY + "Show death and revival animation: \u00b0" + (this.main.getConfigClass().getAnimation() ? ChatColor.GREEN + "true" : ChatColor.RED + "false") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(23, this.main.buildItemStack(Config.getTombstone() ? Material.GREEN_TERRACOTTA : Material.RED_TERRACOTTA, Config.getTombstone() ? ChatColor.GREEN + "Tombstone" : ChatColor.RED + "Tombstone", ChatColor.GRAY + "Spawn tombstone after death: \u00b0" + (Config.getTombstone() ? ChatColor.GREEN + "true" : ChatColor.RED + "false") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
        this.settingsInv.setItem(31, this.main.buildItemStack(Material.TOTEM_OF_UNDYING, Config.getTotem() ? ChatColor.GREEN + "Totem" : ChatColor.RED + "Totem", ChatColor.GRAY + "Still get knocked with\u00b0" + ChatColor.GRAY + "a totem in your hand:\u00b0" + (Config.getTotem() ? ChatColor.GREEN + "true" : ChatColor.RED + "false") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to edit]", true));
    }

    public void updateTimeInv() {
        this.timeInv.setItem(1, this.main.buildItemStack(Material.REDSTONE_TORCH, ChatColor.RED + "-", ChatColor.GRAY + "Lower the time by\u00b0" + ChatColor.DARK_GRAY + "30 " + ChatColor.GRAY + "seconds\u00b0\u00b0" + ChatColor.GRAY + "Current time: " + ChatColor.GOLD + this.main.getConfigClass().getTime() / 60 + ChatColor.GOLD + ":" + (this.main.getConfigClass().getTime() % 60 >= 10 ? "" + ChatColor.GOLD + this.main.getConfigClass().getTime() % 60 : ChatColor.GOLD + "0" + ChatColor.GOLD + this.main.getConfigClass().getTime() % 60) + (this.main.getConfigClass().getTime() / 60 > 1 ? ChatColor.GOLD + " minutes" : ChatColor.GOLD + " minute") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to lower]", true));
        this.timeInv.setItem(4, this.main.buildItemStack(Material.EMERALD_BLOCK, ChatColor.GOLD + "Return to settings", "\u00b0" + ChatColor.YELLOW + "[Click to return]", true));
        this.timeInv.setItem(7, this.main.buildItemStack(Material.TORCH, ChatColor.GREEN + "+", ChatColor.GRAY + "Higher the time by\u00b0" + ChatColor.DARK_GRAY + "30 " + ChatColor.GRAY + "seconds\u00b0\u00b0" + ChatColor.GRAY + "Current time: " + ChatColor.GOLD + this.main.getConfigClass().getTime() / 60 + ChatColor.GOLD + ":" + (this.main.getConfigClass().getTime() % 60 >= 10 ? "" + ChatColor.GOLD + this.main.getConfigClass().getTime() % 60 : ChatColor.GOLD + "0" + ChatColor.GOLD + this.main.getConfigClass().getTime() % 60) + (this.main.getConfigClass().getTime() / 60 > 1 ? ChatColor.GOLD + " minutes" : ChatColor.GOLD + " minute") + "\u00b0\u00b0" + ChatColor.YELLOW + "[Click to higher]", true));
    }

    public void updateDetectionRangeInv() {
        this.detectionRangeInv.setItem(1, this.main.buildItemStack(Material.REDSTONE_TORCH, ChatColor.RED + "-", ChatColor.GRAY + "Lower the detection range by\u00b0" + ChatColor.DARK_GRAY + "5 " + ChatColor.GRAY + "blocks\u00b0\u00b0" + ChatColor.GRAY + "Current detection range: " + ChatColor.GOLD + this.main.getConfigClass().getDetectionRange() + ChatColor.GRAY + " blocks\u00b0\u00b0" + ChatColor.YELLOW + "[Click to lower]", true));
        this.detectionRangeInv.setItem(4, this.main.buildItemStack(Material.EMERALD_BLOCK, ChatColor.GOLD + "Return to settings", "\u00b0" + ChatColor.YELLOW + "[Click to return]", true));
        this.detectionRangeInv.setItem(7, this.main.buildItemStack(Material.TORCH, ChatColor.GREEN + "+", ChatColor.GRAY + "Higher the detection range\u00b0" + ChatColor.DARK_GRAY + "5 " + ChatColor.GRAY + "blocks\u00b0\u00b0" + ChatColor.GRAY + "Current detection range: " + ChatColor.GOLD + this.main.getConfigClass().getDetectionRange() + ChatColor.GRAY + " blocks\u00b0\u00b0" + ChatColor.YELLOW + "[Click to higher]", true));
    }

    public void openSettingsInv(Player player) {
        player.openInventory(this.settingsInv);
    }

    public void openTimeInv(Player player) {
        player.openInventory(this.timeInv);
    }

    public void openDetectionRangeInv(Player player) {
        player.openInventory(this.detectionRangeInv);
    }

    public Inventory getSettingsInv() {
        return this.settingsInv;
    }

    public Inventory getTimeInv() {
        return this.timeInv;
    }

    public Inventory getDetectionRangeInv() {
        return this.detectionRangeInv;
    }

    public Revive getMain() {
        return this.main;
    }
}

