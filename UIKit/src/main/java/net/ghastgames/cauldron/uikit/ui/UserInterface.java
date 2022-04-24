package net.ghastgames.cauldron.uikit.ui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class UserInterface {
    String title;
    int slots;
    Inventory inventory;

    public UserInterface(String title, int rows) {
        this.title = title;
        if(rows > 6) {
            throw new IllegalArgumentException("Too many rows");
        }
        this.slots = rows * 9; // rows * columns

        inventory = Bukkit.createInventory(new UIKitInventoryHolder(UUID.randomUUID()), this.slots, title);
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

    public void setItem(int index, UIItem item) {
        inventory.setItem(index, item.getItemStack());
    }


}
