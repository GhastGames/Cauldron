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

    public void saveLocal(String path, Object object) {
        PluginKit.getPlugin().getConfig().set(path, object);
    }

    public Object getLocal(String path) {
        return PluginKit.getPlugin().getConfig().get(path);
    }
}
