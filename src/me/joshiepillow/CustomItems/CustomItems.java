package me.joshiepillow.CustomItems;

import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CustomItems {
    private static NamespacedKey NamespacedKey;
    private static List<CustomItems> list = new ArrayList<>();

    private static CustomItems DEFAULT = new CustomItems();

    static void setNamespacedKey(JavaPlugin plugin) {
        NamespacedKey = new NamespacedKey(plugin, "CustomItems");
    }
    static NamespacedKey getNamespacedKey() {
        return NamespacedKey;
    }
    private static void checkNamespacedKey() {
        if (NamespacedKey == null) throw new NullPointerException("Must init key first. [run setKey(plugin)]");
    }
    static CustomItems getByItem(ItemStack i) {
        checkNamespacedKey();
        if (i != null &&
            i.getItemMeta() != null &&
            i.getItemMeta().getPersistentDataContainer().has(NamespacedKey, PersistentDataType.INTEGER) &&
            i.getItemMeta().getPersistentDataContainer().get(NamespacedKey, PersistentDataType.INTEGER) < list.size())
            return list.get(i.getItemMeta().getPersistentDataContainer().get(NamespacedKey, PersistentDataType.INTEGER));
        return DEFAULT;
    }


    private int personalKey;

    public Consumer<InventoryClickEvent> onInventoryClickEvent;
    public Consumer<PlayerDropItemEvent> onPlayerDropItemEvent;
    public Consumer<BlockPlaceEvent> onBlockPlaceEvent;
    public Consumer<PlayerInteractEvent> onPlayerInteractEvent;
    public BiConsumer<EntityDeathEvent, ItemStack> onEntityDeathEvent;
    public Consumer<EntityDamageByEntityEvent> onEntityDamageByEntityEvent;
    public Consumer<PlayerSwapHandItemsEvent> onPlayerSwapHandItemsEvent;

    public int getKey() {
        return personalKey;
    }
    public CustomItems() {
        personalKey = list.size();
        list.add(this);
        onInventoryClickEvent = event -> {};
        onPlayerDropItemEvent = event -> {};
        onBlockPlaceEvent = event -> {};
        onPlayerInteractEvent = event -> {};
        onEntityDeathEvent = (event, item) -> {};
        onEntityDamageByEntityEvent = event -> {};
        onPlayerSwapHandItemsEvent = event -> {};
    }
    public CustomItems(CustomItems c) {
        this();
        onInventoryClickEvent = c.onInventoryClickEvent;
        onPlayerDropItemEvent = c.onPlayerDropItemEvent;
        onBlockPlaceEvent = c.onBlockPlaceEvent;
        onPlayerInteractEvent = c.onPlayerInteractEvent;
        onEntityDeathEvent = c.onEntityDeathEvent;
        onEntityDamageByEntityEvent = c.onEntityDamageByEntityEvent;
        onPlayerSwapHandItemsEvent = c.onPlayerSwapHandItemsEvent;
    }
    public ItemStack setPersistentData(ItemStack i) {
        ItemMeta m = i.getItemMeta();
        assert m != null;
        m.getPersistentDataContainer().set(NamespacedKey, PersistentDataType.INTEGER, personalKey);
        i.setItemMeta(m);
        return i;
    }
}
