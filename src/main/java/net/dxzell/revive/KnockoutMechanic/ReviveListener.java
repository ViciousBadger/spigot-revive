/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.event.entity.EntityRegainHealthEvent
 *  org.bukkit.event.entity.PlayerDeathEvent
 *  org.bukkit.event.player.PlayerInteractAtEntityEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.inventory.ItemStack
 *  org.spigotmc.event.entity.EntityDismountEvent
 */
package com.dxzell.revive.KnockoutMechanic;

import com.dxzell.revive.Config;
import com.dxzell.revive.KnockoutMechanic.DownedPlayer;
import com.dxzell.revive.Revive;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.entity.EntityDismountEvent;

public class ReviveListener
implements Listener {
    private DownedPlayer downedPlayer;
    private Revive main;
    private HashMap<Block, ItemStack[]> chestContents = new HashMap();

    public ReviveListener(DownedPlayer downedPlayer, Revive main) {
        this.main = main;
        this.downedPlayer = downedPlayer;
    }

    @EventHandler
    public void onTot(PlayerDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = e.getEntity();
            if (this.downedPlayer.getRevivalList().contains(player)) {
                e.setDeathMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Config.getPlayerDiedMessage().replace("[player]", player.getName())));
                this.downedPlayer.resetMaps(player);
                if (Config.getTombstone()) {
                    this.chestContents.put(this.downedPlayer.placeTombstone(player), (ItemStack[])player.getInventory().getContents().clone());
                    e.getDrops().clear();
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (this.downedPlayer.getPlayerStands().containsKey(e.getPlayer())) {
            e.setCancelled(true);
        }
        if (e.getClickedBlock() != null && this.chestContents.containsKey(e.getClickedBlock())) {
            for (ItemStack stack : this.chestContents.get(e.getClickedBlock())) {
                if (stack == null) continue;
                e.getClickedBlock().getLocation().getWorld().dropItemNaturally(e.getClickedBlock().getLocation(), stack);
            }
            this.chestContents.remove(e.getClickedBlock());
            e.getClickedBlock().setType(Material.AIR);
        }
    }

    @EventHandler
    public void onDismount(EntityDismountEvent e) {
        if (e.getEntity() instanceof Player && this.downedPlayer.getPlayerStands().containsKey((Player)e.getEntity())) {
            Player player = (Player)e.getEntity();
            if (this.downedPlayer.getRevivalList().contains(player) && !this.downedPlayer.playerAround(player)) {
                if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) || player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING)) {
                    player.setHealth(1.0);
                    player.getInventory().removeItem(new ItemStack[]{new ItemStack(Material.TOTEM_OF_UNDYING, 1)});
                    player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                    this.downedPlayer.revivePlayer(player);
                } else {
                    this.downedPlayer.killPlayer(player);
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (this.downedPlayer.getPlayerStands().containsKey(e.getPlayer())) {
            e.getPlayer().setHealth(0.0);
            this.downedPlayer.resetMaps(e.getPlayer());
        }
    }

    @EventHandler
    public void onDestroyBlocks(BlockBreakEvent e) {
        if (this.downedPlayer.getPlayerStands().containsKey(e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamageOtherEntity(EntityDamageByEntityEvent e) {
        if (this.downedPlayer.getPlayerStands().containsKey(e.getDamager())) {
            e.setCancelled(true);
        } else if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (this.downedPlayer.getRevivalList().contains((Player)e.getEntity())) {
                if (!(e.getDamager() instanceof Player)) {
                    if (!this.main.getConfigClass().getMobDamage()) {
                        e.setCancelled(true);
                    } else if (player.getHealth() - e.getDamage() <= 0.0 && (player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) || player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING))) {
                        e.setCancelled(true);
                        player.setHealth(1.0);
                        player.getInventory().removeItem(new ItemStack[]{new ItemStack(Material.TOTEM_OF_UNDYING, 1)});
                        player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                        this.downedPlayer.revivePlayer(player);
                    }
                } else if (player.getHealth() - e.getDamage() <= 0.0 && (player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) || player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING))) {
                    e.setCancelled(true);
                    player.setHealth(1.0);
                    player.getInventory().removeItem(new ItemStack[]{new ItemStack(Material.TOTEM_OF_UNDYING, 1)});
                    player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                    this.downedPlayer.revivePlayer(player);
                }
            } else if (!this.downedPlayer.getRevivalList().contains(player) && player.getHealth() - e.getDamage() <= 0.0) {
                if ((player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) || player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING)) && Config.getTotem()) {
                    e.setCancelled(true);
                    this.downedPlayer.addRevivelList(player);
                    this.downedPlayer.downPlayer(player);
                    player.setHealth(1.0);
                } else if (!player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) && !player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING)) {
                    e.setCancelled(true);
                    this.downedPlayer.addRevivelList(player);
                    this.downedPlayer.downPlayer(player);
                    player.setHealth(1.0);
                }
            }
        }
    }

    @EventHandler
    public void onDam(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (e.getCause().equals((Object)EntityDamageEvent.DamageCause.FALL) || e.getCause().equals((Object)EntityDamageEvent.DamageCause.LAVA) || e.getCause().equals((Object)EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals((Object)EntityDamageEvent.DamageCause.FIRE_TICK) || e.getCause().equals((Object)EntityDamageEvent.DamageCause.DROWNING) || e.getCause().equals((Object)EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) || e.getCause().equals((Object)EntityDamageEvent.DamageCause.SUICIDE)) {
                if (this.downedPlayer.getRevivalList().contains((Player)e.getEntity())) {
                    if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) || player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING)) {
                        e.setCancelled(true);
                        player.setHealth(1.0);
                        player.getInventory().removeItem(new ItemStack[]{new ItemStack(Material.TOTEM_OF_UNDYING, 1)});
                        player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                        this.downedPlayer.revivePlayer(player);
                    }
                } else if (!this.downedPlayer.getRevivalList().contains(player) && player.getHealth() - e.getDamage() <= 0.0) {
                    if ((player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) || player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING)) && Config.getTotem()) {
                        e.setCancelled(true);
                        this.downedPlayer.addRevivelList(player);
                        this.downedPlayer.downPlayer(player);
                        player.setHealth(1.0);
                    } else if (!player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) && !player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING)) {
                        e.setCancelled(true);
                        this.downedPlayer.addRevivelList(player);
                        this.downedPlayer.downPlayer(player);
                        player.setHealth(1.0);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRegenerate(EntityRegainHealthEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (this.downedPlayer.getRevivalList().contains(player)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteractWithEntity(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            Player player = (Player)e.getRightClicked();
            if (this.downedPlayer.getPlayerStands().containsKey(e.getRightClicked())) {
                this.downedPlayer.revivePlayer(player);
                // e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!this.downedPlayer.getPlayerStands().containsKey(e.getPlayer())) {
            for (Player player : this.downedPlayer.getPlayerStands().keySet()) {
                this.downedPlayer.setBossBar(player);
            }
        }
    }
}

