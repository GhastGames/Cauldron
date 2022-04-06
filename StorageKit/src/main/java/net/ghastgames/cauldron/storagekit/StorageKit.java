package net.ghastgames.cauldron.storagekit;

import net.ghastgames.cauldron.pluginkit.PluginKit;

public class StorageKit {
    private static StorageKit instance;

    static {
        PluginKit.getPlugin().saveConfig();
    }

    public static StorageKit getInstance() {
        if(instance == null) instance = new StorageKit();
        return instance;
    }

    public void
}
