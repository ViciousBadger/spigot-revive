/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package com.dxzell.revive.Settings;

import com.dxzell.revive.Config;
import com.dxzell.revive.Settings.Settings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SettingsCommand
implements CommandExecutor {
    private Settings settings;

    public SettingsCommand(Settings settings) {
        this.settings = settings;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("revive")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("settings")) {
                        this.settings.openSettingsInv(player);
                    } else if (args[0].equalsIgnoreCase("message")) {
                        if (player.hasPermission(Config.getPermission())) {
                            this.settings.getMain().getInv().openInv(player);
                        } else {
                            player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.RED + "Missing permission!");
                        }
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        if (player.hasPermission(Config.getPermission())) {
                            this.settings.getMain().getDownedPlayer().resetAllMaps();
                        } else {
                            player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.RED + "Missing permission!");
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)this.settings.getMain().getConfigClass().getWrongUsage()));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)this.settings.getMain().getConfigClass().getWrongUsage()));
                }
            }
        }
        return false;
    }
}

