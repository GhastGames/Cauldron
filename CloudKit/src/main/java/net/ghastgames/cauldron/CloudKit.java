package net.ghastgames.cauldron;

public class CloudKit {
    private CloudKit instance;

    public CloudKit getInstance() {
        if(instance == null) instance = new CloudKit();
        return instance;
    }
}
