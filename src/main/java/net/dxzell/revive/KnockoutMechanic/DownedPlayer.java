/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.md_5.bungee.api.ChatMessageType
 *  net.md_5.bungee.api.chat.TextComponent
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.block.data.Directional
 *  org.bukkit.boss.BarColor
 *  org.bukkit.boss.BarFlag
 *  org.bukkit.boss.BarStyle
 *  org.bukkit.boss.BossBar
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package com.dxzell.revive.KnockoutMechanic;

import com.dxzell.revive.Config;
import com.dxzell.revive.Revive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DownedPlayer {
    private HashMap<Player, List<ArmorStand>> playerStands = new HashMap();
    private HashMap<Player, Integer> remainingTime = new HashMap();
    private HashMap<Player, Integer> runnableMap = new HashMap();
    private Revive main;
    private BossBar noPlayerAround;
    private BossBar playerAround;
    private List<Player> revivalList = new ArrayList<Player>();

    public DownedPlayer(Revive main) {
        this.main = main;
        main.getConfigClass();
        this.noPlayerAround = Bukkit.createBossBar((String)ChatColor.translateAlternateColorCodes((char)'&', (String)Config.getPressSneak()), (BarColor)BarColor.GREEN, (BarStyle)BarStyle.SOLID, (BarFlag[])new BarFlag[0]);
        main.getConfigClass();
        this.playerAround = Bukkit.createBossBar((String)ChatColor.translateAlternateColorCodes((char)'&', (String)Config.getCantPressSneak()), (BarColor)BarColor.RED, (BarStyle)BarStyle.SOLID, (BarFlag[])new BarFlag[0]);
    }

    public void downPlayer(Player player) {
        String[] text = new String[]{ChatColor.AQUA + "" + this.main.getConfigClass().getTime() / 60 + (this.main.getConfigClass().getTime() % 60 > 0 ? ChatColor.AQUA + ":30" : ChatColor.AQUA + ":00"), ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getArmorStand(0).replace("[item]", this.main.getConfigClass().getType())), ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getArmorStand(1).replace("[item]", this.main.getConfigClass().getType()))};
        Location loc = player.getLocation().clone().add(0.0, 0.7, 0.0);
        ArrayList<ArmorStand> stands = new ArrayList<ArmorStand>();
        ArmorStand playerStand = (ArmorStand)player.getWorld().spawnEntity(player.getLocation().subtract(0.0, 1.7, 0.0), EntityType.ARMOR_STAND);
        playerStand.setInvisible(true);
        playerStand.setGravity(false);
        playerStand.setInvulnerable(true);
        playerStand.addPassenger((Entity)player);
        stands.add(playerStand);
        this.setBossBar(player);
        for (String eachText : text) {
            ArmorStand stand = (ArmorStand)player.getWorld().spawnEntity(loc.subtract(0.0, 0.3, 0.0), EntityType.ARMOR_STAND);
            stand.setInvisible(true);
            stand.setGravity(false);
            stand.setInvulnerable(true);
            stand.setCustomNameVisible(true);
            stand.setCustomName(eachText);
            stands.add(stand);
        }
        this.playerStands.put(player, stands);
        this.remainingTime.put(player, this.main.getConfigClass().getTime());
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, this.main.getConfigClass().getTime() * 20, 1));
        this.updateTime(player);
        player.sendTitle(ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getTitle(true)), ChatColor.translateAlternateColorCodes((char)'&', (String)this.main.getConfigClass().getTitle(false)));
        if (this.main.getConfigClass().getAnimation()) {
            player.getWorld().spawnParticle(Particle.CRIT, player.getLocation().add(0.0, 1.0, 0.0), 100);
        }
        Bukkit.getServer().getOnlinePlayers().forEach(p -> {
            if (!p.getUniqueId().equals(player.getUniqueId())) {
                p.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)Config.getPlayerKnockedMessage(player.getName())));
            }
        });
    }

    public void updateTime(final Player player) {
        int runnable = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this.main, new Runnable(){

            @Override
            public void run() {
                if ((Integer)DownedPlayer.this.remainingTime.get(player) == 1) {
                    if (player.getInventory().getItemInMainHand().getType().equals((Object)Material.TOTEM_OF_UNDYING) || player.getInventory().getItemInOffHand().getType().equals((Object)Material.TOTEM_OF_UNDYING)) {
                        player.setHealth(1.0);
                        player.getInventory().removeItem(new ItemStack[]{new ItemStack(Material.TOTEM_OF_UNDYING, 1)});
                        player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                    } else {
                        DownedPlayer.this.killPlayer(player);
                    }
                } else {
                    DownedPlayer.this.remainingTime.put(player, (Integer)DownedPlayer.this.remainingTime.get(player) - 1);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText((String)(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes((char)'&', (String)DownedPlayer.this.main.getConfigClass().getActionbar()) + " " + ChatColor.DARK_RED + "" + (Integer)DownedPlayer.this.remainingTime.get(player) / 60 + ChatColor.DARK_RED + ":" + ((Integer)DownedPlayer.this.remainingTime.get(player) % 60 >= 10 ? "" + ChatColor.DARK_RED + (Integer)DownedPlayer.this.remainingTime.get(player) % 60 : ChatColor.DARK_RED + "0" + ChatColor.DARK_RED + ChatColor.DARK_RED + (Integer)DownedPlayer.this.remainingTime.get(player) % 60))));
                    try {
                        ArmorStand stand = (ArmorStand)((List)DownedPlayer.this.playerStands.get(player)).get(1);
                        stand.setCustomName(ChatColor.AQUA + "" + (Integer)DownedPlayer.this.remainingTime.get(player) / 60 + ChatColor.AQUA + ":" + ((Integer)DownedPlayer.this.remainingTime.get(player) % 60 >= 10 ? "" + ChatColor.AQUA + (Integer)DownedPlayer.this.remainingTime.get(player) % 60 : ChatColor.AQUA + "0" + ChatColor.AQUA + ChatColor.AQUA + (Integer)DownedPlayer.this.remainingTime.get(player) % 60));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Bukkit.getScheduler().cancelTask(((Integer)DownedPlayer.this.runnableMap.get(player)).intValue());
                    }
                }
            }
        }, 0L, 20L);
        this.runnableMap.put(player, runnable);
    }

    public Block placeTombstone(Player player) {
        Block block = player.getLocation().add(0.0, 1.0, 0.0).getBlock();
        block.setType(Material.CHEST);
        if (block.getBlockData() instanceof Directional) {
            Directional dir = (Directional)block.getBlockData();
            dir.setFacing(player.getFacing());
            block.setBlockData((BlockData)dir);
            block.getState().update();
        }
        BlockFace direction = player.getFacing();
        Location currentLoc = player.getLocation().clone().add(0.0, 1.0, 0.0);
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(currentLoc.add(direction == BlockFace.WEST ? 1.0 : (direction == BlockFace.EAST ? -1.0 : 0.0), 0.0, direction == BlockFace.NORTH ? 1.0 : (direction == BlockFace.SOUTH ? -1.0 : 0.0)));
        locations.add(currentLoc.clone().add(0.0, 1.0, 0.0));
        locations.add(currentLoc.clone().add(0.0, 2.0, 0.0));
        locations.add(currentLoc.clone().add(0.0, 3.0, 0.0));
        locations.add(currentLoc.clone().add(direction == BlockFace.NORTH ? 1.0 : (direction == BlockFace.SOUTH ? -1.0 : 0.0), 2.0, direction == BlockFace.WEST ? -1.0 : (direction == BlockFace.EAST ? 1.0 : 0.0)));
        locations.add(currentLoc.clone().add(direction == BlockFace.NORTH ? -1.0 : (direction == BlockFace.SOUTH ? 1.0 : 0.0), 2.0, direction == BlockFace.WEST ? 1.0 : (direction == BlockFace.EAST ? -1.0 : 0.0)));
        locations.forEach(loc -> loc.getBlock().setType(Material.STONE));
        return block;
    }

    public void removeStands(Player player) {
        if (this.playerStands.containsKey(player)) {
            for (ArmorStand stand : this.playerStands.get(player)) {
                stand.remove();
            }
            this.playerStands.remove(player);
        }
    }

    public void killPlayer(Player player) {
        player.setHealth(0.0);
    }

    public void resetMaps(Player player) {
        this.revivalList.remove(player);
        this.remainingTime.remove(player);
        this.removeStands(player);
        this.removeBossBar(player);
        if (this.runnableMap.get(player) != null) {
            Bukkit.getScheduler().cancelTask(this.runnableMap.get(player).intValue());
        }
        this.runnableMap.remove(player);
        player.removePotionEffect(PotionEffectType.BLINDNESS);
        this.removeFromRevival(player);
    }

    public void resetAllMaps() {
        ArrayList cloneList = new ArrayList();
        this.revivalList.forEach(p -> cloneList.add(p));
        cloneList.forEach(p -> {
            this.resetMaps((Player)p);
            p.teleport(p.getLocation().clone().add(0.0, 1.0, 0.0));
        });
        Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "Reloaded Revive Plugin");
    }

    public void revivePlayer(Player player) {
        this.resetMaps(player);
        this.removeBossBar(player);
        if (this.main.getConfigClass().getAnimation()) {
            player.getWorld().spawnParticle(Particle.HEART, player.getLocation().add(0.0, 1.0, 0.0), 100);
        }
        player.teleport(new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1.0, player.getLocation().getZ()));
    }

    public void setBossBar(Player player) {
        this.removeBossBar(player);
        if (this.playerAround(player)) {
            this.playerAround.addPlayer(player);
        } else {
            this.noPlayerAround.addPlayer(player);
        }
    }

    public void removeBossBar(Player player) {
        if (this.noPlayerAround.getPlayers().contains(player)) {
            this.noPlayerAround.removePlayer(player);
        } else if (this.playerAround.getPlayers().contains(player)) {
            this.playerAround.removePlayer(player);
        }
    }

    public boolean playerAround(Player player) {
        for (Entity ent : player.getNearbyEntities((double)this.main.getConfigClass().getDetectionRange(), (double)this.main.getConfigClass().getDetectionRange(), (double)this.main.getConfigClass().getDetectionRange())) {
            if (!(ent instanceof Player)) continue;
            return true;
        }
        return false;
    }

    public void updateBossbars() {
        this.main.getConfigClass();
        this.noPlayerAround.setTitle(ChatColor.translateAlternateColorCodes((char)'&', (String)Config.getPressSneak()));
        this.main.getConfigClass();
        this.playerAround.setTitle(ChatColor.translateAlternateColorCodes((char)'&', (String)Config.getCantPressSneak()));
    }

    public HashMap<Player, List<ArmorStand>> getPlayerStands() {
        return this.playerStands;
    }

    public List<Player> getRevivalList() {
        return this.revivalList;
    }

    public void addRevivelList(Player player) {
        this.revivalList.add(player);
    }

    public void removeFromRevival(Player player) {
        this.revivalList.remove(player);
    }
}

