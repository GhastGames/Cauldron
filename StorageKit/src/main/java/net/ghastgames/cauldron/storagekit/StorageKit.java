package net.ghastgames.cauldron.storagekit;

public class StorageKit {
    private static StorageKit instance;

    public static StorageKit getInstance() {
        if(instance == null) instance = new StorageKit();
        return instance;
    }

    
}
