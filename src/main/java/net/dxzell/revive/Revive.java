/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.TabCompleter
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.java.JavaPlugin
 */
package com.dxzell.revive;

import com.dxzell.revive.Config;
import com.dxzell.revive.CustomMessages.CustomInventory;
import com.dxzell.revive.CustomMessages.InventoryListener;
import com.dxzell.revive.KnockoutMechanic.DownedPlayer;
import com.dxzell.revive.KnockoutMechanic.ReviveListener;
import com.dxzell.revive.Settings.Settings;
import com.dxzell.revive.Settings.SettingsCommand;
import com.dxzell.revive.Settings.SettingsCommandTab;
import com.dxzell.revive.Settings.SettingsListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Revive
extends JavaPlugin {
    private Config config = new Config(this);
    private DownedPlayer downedPlayer = new DownedPlayer(this);
    private Settings settings = new Settings(this);
    private CustomInventory customInv = new CustomInventory(this);

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents((Listener)new ReviveListener(this.downedPlayer, this), (Plugin)this);
        this.getCommand("revive").setExecutor((CommandExecutor)new SettingsCommand(this.settings));
        Bukkit.getPluginManager().registerEvents((Listener)new SettingsListener(this.settings, this), (Plugin)this);
        this.getCommand("revive").setTabCompleter((TabCompleter)new SettingsCommandTab(this.settings));
        Bukkit.getPluginManager().registerEvents((Listener)new InventoryListener(this.customInv, this), (Plugin)this);
    }

    public ItemStack buildItemStack(Material mat, String displayName, String lore, boolean enchantment) {
        ItemStack stack = new ItemStack(mat);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        String[] splitLore = lore.split("\u00b0");
        ArrayList<String> loreList = new ArrayList<String>();
        for (String split : splitLore) {
            loreList.add(split);
        }
        meta.setLore(loreList);
        if (enchantment) {
            meta.addEnchant(Enchantment.KNOCKBACK, 0, false);
        }
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        stack.setItemMeta(meta);
        return stack;
    }

    public Config getConfigClass() {
        return this.config;
    }

    public HashMap<Player, List<ArmorStand>> getStands() {
        return this.downedPlayer.getPlayerStands();
    }

    public DownedPlayer getDownedPlayer() {
        return this.downedPlayer;
    }

    public CustomInventory getInv() {
        return this.customInv;
    }
}

