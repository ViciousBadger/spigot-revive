/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.player.PlayerChatEvent
 */
package com.dxzell.revive.CustomMessages;

import com.dxzell.revive.Config;
import com.dxzell.revive.CustomMessages.CustomInventory;
import com.dxzell.revive.Revive;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class InventoryListener
implements Listener {
    private Revive main;
    private CustomInventory inv;
    private HashMap<Player, String> cause = new HashMap();

    public InventoryListener(CustomInventory inv, Revive main) {
        this.main = main;
        this.inv = inv;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(ChatColor.WHITE + "Customize messages " + ChatColor.BLACK + ">>")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
                return;
            }
            Player player = (Player)e.getWhoClicked();
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Message above player")) {
                this.cause.put((Player)e.getWhoClicked(), "above0");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type the first line of the message in the chat. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Title and Subtitle")) {
                this.cause.put((Player)e.getWhoClicked(), "title");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type the upper title in the chat. If you wanna use the standard item name (f.e. GOLDEN_APPLE) in the title or subtitle, use [item]. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Actionbar")) {
                this.cause.put((Player)e.getWhoClicked(), "actionbar");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type the actionbar message in the chat. The timer will always be at the end of the message. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Wrong command usage")) {
                this.cause.put((Player)e.getWhoClicked(), "wrong");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type the wrong command usage message in the chat. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "New revive item message")) {
                this.cause.put((Player)e.getWhoClicked(), "new");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type new revive item message in the chat. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Player bled out")) {
                this.cause.put((Player)e.getWhoClicked(), "bledOut");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type a new player bled out message in the chat. To implement the players name, write " + ChatColor.GOLD + "[player]" + ChatColor.GRAY + " where you want the player name to be. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Player nearby")) {
                this.cause.put((Player)e.getWhoClicked(), "nearby");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type a new player is nearby message. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Player not nearby")) {
                this.cause.put((Player)e.getWhoClicked(), "notNearby");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type a new player is not nearby message. You should tell the player that he can die by sneaking. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Player knocked")) {
                this.cause.put((Player)e.getWhoClicked(), "playerKnocked");
                player.sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type a new player knocked message. Use ColorCodes with '&' (&4Test).");
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        if (this.cause.containsKey(e.getPlayer())) {
            e.setCancelled(true);
            switch (this.cause.get(e.getPlayer())) {
                case "above0": {
                    this.main.getConfigClass().setArmorStand(e.getMessage(), 0);
                    this.cause.put(e.getPlayer(), "above1");
                    e.getPlayer().sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type the second line in the chat.");
                    this.inv.update();
                    break;
                }
                case "above1": {
                    this.main.getConfigClass().setArmorStand(e.getMessage(), 1);
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                    break;
                }
                case "title": {
                    this.main.getConfigClass().setTitle(e.getMessage(), true);
                    this.cause.put(e.getPlayer(), "subtitle");
                    e.getPlayer().sendMessage(ChatColor.GOLD + "[Revive] " + ChatColor.GRAY + "Now type the subtitle in the chat.");
                    this.inv.update();
                    break;
                }
                case "subtitle": {
                    this.main.getConfigClass().setTitle(e.getMessage(), false);
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                    break;
                }
                case "actionbar": {
                    this.main.getConfigClass().setActionbar(e.getMessage());
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                    break;
                }
                case "wrong": {
                    this.main.getConfigClass().setWrongUsage(e.getMessage());
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                    break;
                }
                case "new": {
                    this.main.getConfigClass().setNewReviveItemMessage(e.getMessage());
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                    break;
                }
                case "bledOut": {
                    this.main.getConfigClass().setPlayerDiedMessage(e.getMessage());
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                    break;
                }
                case "nearby": {
                    this.main.getConfigClass().setCantPressSneak(e.getMessage());
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                    break;
                }
                case "notNearby": {
                    this.main.getConfigClass().setPressSneak(e.getMessage());
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                    break;
                }
                case "playerKnocked": {
                    Config.setPlayerKnockedMessage(e.getMessage());
                    this.cause.remove(e.getPlayer());
                    this.inv.update();
                    this.inv.openInv(e.getPlayer());
                }
            }
        }
    }
}

