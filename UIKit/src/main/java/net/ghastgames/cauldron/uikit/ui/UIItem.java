package net.ghastgames.cauldron.uikit.ui;

import lombok.Builder;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Data
@Builder
public class UIItem {
    String name;
    List<String> lore;
    Material material;
    Enchantment enchantment;
    short subId;
    int amount;
    boolean showEnchantments;

    public UIItem() {}

    ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        meta.setDisplayName(name);
        if(!showEnchantments) meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
        itemStack.addEnchantment(enchantment, 1);
        return itemStack;
    }
}
