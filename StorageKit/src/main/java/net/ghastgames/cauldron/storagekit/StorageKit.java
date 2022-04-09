package net.ghastgames.cauldron.storagekit;

import net.ghastgames.cauldron.pluginkit.PluginKit;

public class StorageKit {
    private static StorageKit instance;

    static {
        try {
            PluginKit.getPlugin().saveConfig();
        } catch(NullPointerException exception) {
            System.out.println("[Cauldron][StorageKit] Can't init config: PluginKit not initialized");
        }
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
