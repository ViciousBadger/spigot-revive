/*
 * Decompiled with CFR 0.152.
 */
package com.dxzell.revive;

import com.dxzell.revive.Revive;

public class Config {
    private static Revive main;

    public Config(Revive main) {
        Config.main = main;
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();
    }

    public int getTime() {
        return main.getConfig().getInt("time");
    }

    public void setTime(boolean higherLower) {
        if (higherLower) {
            if (this.getTime() < 600) {
                main.getConfig().set("time", (Object)(this.getTime() + 30));
                main.saveConfig();
            }
        } else if (this.getTime() > 30) {
            main.getConfig().set("time", (Object)(this.getTime() - 30));
            main.saveConfig();
        }
    }

    public String getType() {
        return main.getConfig().getString("item");
    }

    public void setType(String type) {
        main.getConfig().set("item", (Object)type);
        main.saveConfig();
    }

    public boolean getAnimation() {
        return main.getConfig().getBoolean("animation");
    }

    public void setAnimation() {
        if (this.getAnimation()) {
            main.getConfig().set("animation", (Object)false);
            main.saveConfig();
        } else {
            main.getConfig().set("animation", (Object)true);
            main.saveConfig();
        }
    }

    public String getArmorStand(int index) {
        return main.getConfig().getString("messages.armorstand.stand" + index);
    }

    public void setArmorStand(String message, int index) {
        main.getConfig().set("messages.armorstand.stand" + index, (Object)message);
        main.saveConfig();
    }

    public String getTitle(boolean titleOrSub) {
        if (titleOrSub) {
            return main.getConfig().getString("messages.title.title");
        }
        return main.getConfig().getString("messages.title.subtitle");
    }

    public void setTitle(String message, boolean titleOrSub) {
        if (titleOrSub) {
            main.getConfig().set("messages.title.title", (Object)message);
            main.saveConfig();
        } else {
            main.getConfig().set("messages.title.subtitle", (Object)message);
            main.saveConfig();
        }
    }

    public String getActionbar() {
        return main.getConfig().getString("messages.actionbar");
    }

    public void setActionbar(String message) {
        main.getConfig().set("messages.actionbar", (Object)message);
        main.saveConfig();
    }

    public String getWrongUsage() {
        return main.getConfig().getString("messages.wrongUsage");
    }

    public void setWrongUsage(String message) {
        main.getConfig().set("messages.wrongUsage", (Object)message);
        main.saveConfig();
    }

    public String getNewReviveItemMessage() {
        return main.getConfig().getString("messages.selectNewReviveItem");
    }

    public void setNewReviveItemMessage(String message) {
        main.getConfig().set("messages.selectNewReviveItem", (Object)message);
        main.saveConfig();
    }

    public static String getPressSneak() {
        return main.getConfig().getString("messages.pressSneak");
    }

    public void setPressSneak(String message) {
        main.getConfig().set("messages.pressSneak", (Object)message);
        main.saveConfig();
        main.getDownedPlayer().updateBossbars();
    }

    public static String getCantPressSneak() {
        return main.getConfig().getString("messages.cantPressSneak");
    }

    public void setCantPressSneak(String message) {
        main.getConfig().set("messages.cantPressSneak", (Object)message);
        main.saveConfig();
        main.getDownedPlayer().updateBossbars();
    }

    public int getDetectionRange() {
        return main.getConfig().getInt("detectionRange");
    }

    public void setDetectionRange(boolean higherLower) {
        if (higherLower) {
            main.getConfig().set("detectionRange", (Object)(this.getDetectionRange() + 5));
            main.saveConfig();
        } else if (this.getDetectionRange() > 5) {
            main.getConfig().set("detectionRange", (Object)(this.getDetectionRange() - 5));
            main.saveConfig();
        }
    }

    public boolean getMobDamage() {
        return main.getConfig().getBoolean("mobDamage");
    }

    public void setMobDamage() {
        main.getConfig().set("mobDamage", (Object)(!this.getMobDamage() ? 1 : 0));
        main.saveConfig();
    }

    public static String getPlayerDiedMessage() {
        return main.getConfig().getString("messages.playerDiedMessage");
    }

    public void setPlayerDiedMessage(String message) {
        main.getConfig().set("messages.playerDiedMessage", (Object)message);
        main.saveConfig();
    }

    public static String getPlayerKnockedMessage(String playerName) {
        return main.getConfig().getString("messages.playerKnocked").replace("[player]", playerName);
    }

    public static void setPlayerKnockedMessage(String message) {
        main.getConfig().set("messages.playerKnocked", (Object)message);
        main.saveConfig();
    }

    public static boolean getTombstone() {
        return main.getConfig().getBoolean("tombstone");
    }

    public static void toggleTombstone() {
        main.getConfig().set("tombstone", (Object)(!Config.getTombstone() ? 1 : 0));
        main.saveConfig();
    }

    public static boolean getTotem() {
        return main.getConfig().getBoolean("totem");
    }

    public static void toggleTotem() {
        main.getConfig().set("totem", (Object)(!Config.getTotem() ? 1 : 0));
        main.saveConfig();
    }

    public static String getPermission() {
        return main.getConfig().getString("permission");
    }
}

