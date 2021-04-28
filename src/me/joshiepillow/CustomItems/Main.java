package me.joshiepillow.CustomItems;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        CustomItems.setNamespacedKey(this);
        getServer().getPluginManager().registerEvents(new MyListener(), this);
    }
}
