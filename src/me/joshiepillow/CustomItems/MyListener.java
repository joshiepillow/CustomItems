package me.joshiepillow.CustomItems;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;


class MyListener implements Listener {
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        CustomItems.getByItem(event.getCurrentItem()).onInventoryClickEvent.accept(event);
    }
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        CustomItems.getByItem(event.getItemDrop().getItemStack()).onPlayerDropItemEvent.accept(event);
    }
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        CustomItems.getByItem(event.getItemInHand()).onBlockPlaceEvent.accept(event);
    }
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        CustomItems.getByItem(event.getItem()).onPlayerInteractEvent.accept(event);
    }
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        for (ItemStack itemStack : new ArrayList<>(event.getDrops())) {
            CustomItems.getByItem(itemStack).onEntityDeathEvent.accept(event, itemStack);
        }
    }
    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof LivingEntity && ((LivingEntity) event.getDamager()).getEquipment() != null)
            CustomItems.getByItem(((LivingEntity) event.getDamager()).getEquipment().getItemInMainHand())
                    .onEntityDamageByEntityEvent.accept(event);
    }
    @EventHandler
    public void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event) {
        CustomItems.getByItem(event.getMainHandItem()).onPlayerSwapHandItemsEvent.accept(event);
    }
}
