package net.ghastgames.cauldron.uikit.ui;

import net.ghastgames.cauldron.uikit.ui.button.UIButton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class UserInterface {
    String title;
    int slots;
    Inventory inventory;
    UUID uuid;
    HashMap<Integer, UIButton.ClickAction> clickActions = new HashMap<>();

    public UserInterface(String title, int rows, JavaPlugin plugin) {
        this.title = title;
        if(rows > 6) {
            throw new IllegalArgumentException("Too many rows");
        }
        this.slots = rows * 9; // rows * columns

        uuid = UUID.randomUUID();
        inventory = Bukkit.createInventory(new UIKitInventoryHolder(uuid), this.slots, title);


        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onEvent(InventoryClickEvent event) {
                if(event.getClickedInventory().getHolder() instanceof UIKitInventoryHolder) {
                    if(((UIKitInventoryHolder) event.getClickedInventory().getHolder()).getInventoryID().equals(uuid)) {
                        int slot = event.getSlot();
                        UIButton.ClickAction action = clickActions.get(slot);
                        if(action != null) {
                            action.onClick((Player) event.getWhoClicked());
                        }
                    }
                }
            }
        }, plugin);
    }

    /**
     * Fills the inventory UI with the given item
     * @param item The item
     */
    public void fill(ItemStack item) {
        for (int i = 0; i < slots; i++) {
            inventory.setItem(i, item);
        }
    }

    /**
     * Places an item at the specific slot
     * @param index Slot
     * @param item ItemStack
     */
    public void setItem(int index, UIItem item) {
        inventory.setItem(index, item.getItemStack());
    }

    /**
     * Places a button at the specific slot
     * @param index Slot
     * @param button Button
     */
    public void addButton(int index, UIButton button) {
        inventory.setItem(index, button.getItem().getItemStack());
        clickActions.put(index, button.getAction());
    }
}
