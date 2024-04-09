/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.TabCompleter
 *  org.bukkit.util.StringUtil
 */
package com.dxzell.revive.Settings;

import com.dxzell.revive.Settings.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class SettingsCommandTab
implements TabCompleter {
    private Settings settings;

    public SettingsCommandTab(Settings settings) {
        this.settings = settings;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        ArrayList<String> liste = new ArrayList<String>();
        if (args.length == 1) {
            return (List)StringUtil.copyPartialMatches((String)args[0], Arrays.asList("settings", "message", "reload"), new ArrayList());
        }
        return liste;
    }
}

