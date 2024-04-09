/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 */
package com.dxzell.revive.Settings;

import com.dxzell.revive.Config;
import com.dxzell.revive.Revive;
import com.dxzell.revive.Settings.Settings;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsListener
implements Listener {
    private Settings settings;
    private Revive main;
    private List<Player> pickingItemType = new ArrayList<Player>();

    public SettingsListener(Settings settings, Revive main) {
        this.settings = settings;
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        block8: {
            block17: {
                block19: {
                    block18: {
                        block14: {
                            block16: {
                                block15: {
                                    block6: {
                                        block13: {
                                            block12: {
                                                block11: {
                                                    block10: {
                                                        block9: {
                                                            block7: {
                                                                if (!e.getInventory().equals((Object)this.settings.getSettingsInv())) break block6;
                                                                e.setCancelled(true);
                                                                if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
                                                                    return;
                                                                }
                                                                if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Time")) break block7;
                                                                this.settings.openTimeInv((Player)e.getWhoClicked());
                                                                break block8;
                                                            }
                                                            if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_GRAY + "Item_Type")) break block9;
                                                            if (!this.pickingItemType.contains((Player)e.getWhoClicked())) {
                                                                this.pickingItemType.add((Player)e.getWhoClicked());
                                                                e.getWhoClicked().closeInventory();
                                                                e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getNewReviveItemMessage()));
                                                            }
                                                            break block8;
                                                        }
                                                        if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Animation") && !e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Animation")) break block10;
                                                        this.main.getConfigClass().setAnimation();
                                                        this.settings.updateSettingsInv();
                                                        break block8;
                                                    }
                                                    if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Mob damage")) break block11;
                                                    this.main.getConfigClass().setMobDamage();
                                                    this.settings.updateSettingsInv();
                                                    break block8;
                                                }
                                                if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Detection range")) break block12;
                                                this.settings.openDetectionRangeInv((Player)e.getWhoClicked());
                                                break block8;
                                            }
                                            if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Tombstone") && !e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Tombstone")) break block13;
                                            Config.toggleTombstone();
                                            this.settings.updateSettingsInv();
                                            break block8;
                                        }
                                        if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Totem") && !e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Totem")) break block8;
                                        Config.toggleTotem();
                                        this.settings.updateSettingsInv();
                                        break block8;
                                    }
                                    if (!e.getInventory().equals((Object)this.settings.getTimeInv())) break block14;
                                    e.setCancelled(true);
                                    if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
                                        return;
                                    }
                                    if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "-")) break block15;
                                    this.main.getConfigClass().setTime(false);
                                    this.settings.updateTimeInv();
                                    this.settings.updateSettingsInv();
                                    break block8;
                                }
                                if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Return to settings")) break block16;
                                this.settings.openSettingsInv((Player)e.getWhoClicked());
                                break block8;
                            }
                            if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "+")) break block8;
                            this.main.getConfigClass().setTime(true);
                            this.settings.updateTimeInv();
                            this.settings.updateSettingsInv();
                            break block8;
                        }
                        if (!e.getInventory().equals((Object)this.settings.getDetectionRangeInv())) break block17;
                        e.setCancelled(true);
                        if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
                            return;
                        }
                        if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "-")) break block18;
                        this.main.getConfigClass().setDetectionRange(false);
                        this.settings.updateDetectionRangeInv();
                        this.settings.updateSettingsInv();
                        break block8;
                    }
                    if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Return to settings")) break block19;
                    this.settings.openSettingsInv((Player)e.getWhoClicked());
                    break block8;
                }
                if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "+")) break block8;
                this.main.getConfigClass().setDetectionRange(true);
                this.settings.updateDetectionRangeInv();
                this.settings.updateSettingsInv();
                break block8;
            }
            if (e.getCurrentItem() != null && !e.getCurrentItem().getType().equals((Object)Material.AIR) && this.pickingItemType.contains(e.getWhoClicked())) {
                e.setCancelled(true);
                this.main.getConfigClass().setType(e.getCurrentItem().getType().toString());
                this.settings.updateSettingsInv();
                this.settings.openSettingsInv((Player)e.getWhoClicked());
                this.pickingItemType.remove(e.getWhoClicked());
                for (List<ArmorStand> stands : this.main.getStands().values()) {
                    ArmorStand stand = stands.get(2);
                    stand.setCustomName(ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getArmorStand(0)).replace("[item]", this.main.getConfigClass().getType()));
                    stand = stands.get(3);
                    stand.setCustomName(ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getArmorStand(1)).replace("[item]", this.main.getConfigClass().getType()));
                }
            }
        }
    }
}

