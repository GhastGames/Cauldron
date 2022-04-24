package net.ghastgames.cauldron.uikit.ui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public class UIKitInventoryHolder implements InventoryHolder {
    private UUID inventoryID;

    public UIKitInventoryHolder(UUID inventoryID) {
        this.inventoryID = UUID.randomUUID();
    }

    @Override
    public Inventory getInventory() {
        return UIStore.getStore().get(inventoryID).inventory;
    }
}
