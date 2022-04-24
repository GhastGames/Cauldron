package net.ghastgames.cauldron.uikit.ui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

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
}
